echo "🔍 Executando analise do sonar"
cd ../
mvn verify sonar:sonar -Dsonar.login=SONAR_LOGIN -Dsonar.organization="SONAR_KEY" -Dsonar.projectKey=SONAR_KEY