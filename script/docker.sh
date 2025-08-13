#!/usr/bin/env bash
set -euo pipefail

# set -euo pipefail - torna a execução mais segura e previsível
# -e: pare no primeiro erro
# -u: erro se variável não estiver definida
# -o pipefail: detecta falha em qualquer parte do pipeline

# Verifica se a variável de API está definida
if [[ -z "${VULTR_API_KEY:-}" ]]; then
  echo "❌ A variável VULTR_API_KEY não está definida."
  echo "Defina com: export VULTR_API_KEY=seu_token"
  exit 1
fi

# 0 - copia arquivos para o diretório de trabalho
cp ../Dockerfile .
cp -rf ../src .
cp ../pom.xml .

# 1 - Lista registries
echo "🔍 Consultando container registries no Vultr Cloud."
registries_json=$(curl -s -H "Authorization: Bearer ${VULTR_API_KEY}" \
    "https://api.vultr.com/v2/registries")

if [ "$(echo "$registries_json" | jq '.registries | length')" -eq 0 ]; then
  echo "❌ Não foi encontrado registry provisionado no ambiente."
  exit 1
fi

export VULTR_REGISTRY_NAME= "kuberneteslabcontainerregistry"

# 2 - Pega o ID do registry
registry_id=$(echo $registries_json | jq -r '.registries[] | select(.name | contains("'${VULTR_REGISTRY_NAME}'")) | .id')

#echo "registry_id: ${registry_id}"

export VULTR_REGISTRY_ID=${registry_id}
export VULTR_LOGIN=$(echo $registries_json | jq -r '.registries[].root_user | .username')
export VULTR_PASSWORD=$(echo $registries_json | jq -r '.registries[].root_user | .password')
export VULTR_URN=$(echo $registries_json | jq -r '.registries[] | .urn')
export VULTR_URN_FULL='https://'${VULTR_URN}
export VULTR_PROJECT_NAME="kubernetes-labs-app-micronout"
export VULTR_TAG="SNAPSHOT"

#echo "login: ${VULTR_LOGIN}"
#echo "password: ${VULTR_PASSWORD}"
#echo "urn: ${VULTR_URN_FULL}"

echo "🐳 Login no Vultr Container Registry"
docker logout ${VULTR_URN_FULL}
echo ${VULTR_PASSWORD} | docker login ${VULTR_URN_FULL} -u ${VULTR_LOGIN} --password-stdin

echo "🛠️ Build da imagem Docker"
docker build -t ${VULTR_URN}/${VULTR_PROJECT_NAME}:${VULTR_TAG} -f ./Dockerfile .

echo "📤 Push da imagem"
docker push ${VULTR_URN}/${VULTR_PROJECT_NAME}:${VULTR_TAG}

echo "👋🏾 Logout do Vultr Container Registry"
docker logout ${VULTR_URN_FULL}

echo "✅ Imagem enviada com sucesso para o Vultr Container Registry"

# Limpa os arquivos temporários
rm -rf ./src
rm -f ./Dockerfile
rm -f ./pom.xml