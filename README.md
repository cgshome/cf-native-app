# Cloud Foundary MySQL과 자동 바인딩하는 Spring Boot App 사례  

## PaaS 환경 실행
- 설치
```
git clone https://github.com/cgshome/cf-native-app
cd cf-native-app
```

- 서비스 인스턴스 생성
```
cf create-service p-mysql 200mb mariadb-datasource  
```

- 설정 변경
```
vi manifest.yml
---
applications:
- name: cf-native-app     # <-- 어플리케이션명 변경
  memory: 512M
  instances: 1
  host: demo-cf-datasource     # <-- 호스트명 변경
  path: target/cf-native-app-0.0.1-SNAPSHOT.war
  buildpack: java_buildpack
  services:
   - mariadb-datasource    # <-- 서비스 인스터스명 변경
```

- 어플리케이션 배포
```
mvn install
cf push
```

- 로그 확인 
```
cf logs cf-native-app --recent
```

## 로컬 환경 실행
- 설치
```
git clone https://github.com/cgshome/cf-native-app
cd cf-native-app
```
- 실행
```
mvn install
java -jar target/cf-native-app-0.0.1-SNAPSHOT.war paasxpert.demo.cf.datasource.CloudApplication
```


