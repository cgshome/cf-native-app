# PaaSXpert 환경에서 Spring boot을 이용한 데이터 서비스 바이딩

## PaaS 환경 실행
- 설치
```
git clone https://github.com/CrossentCloud/PaaSXpert-Tutorial
cd PaaSXpert-Tutorial/demo-cf-datasource
```

- 서비스 인스턴스 생성
```
cf create-service p-mysql 200mb cf-datasource-db  
```

- 설정 변경
```
vi manifest.yml
---
applications:
- name: demo-cf-datasource     # <-- 어플리케이션명 변경
  memory: 512M
  instances: 1
  host: demo-cf-datasource     # <-- 호스트명 변경
  path: target/demo-cf-datasource-0.0.1-SNAPSHOT.war
  buildpack: java_buildpack
  services:
   - demo-cf-datasource-db    # <-- 서비스 인스터스명 변경
```

- 어플리케이션 배포
```
mvn install
cf push
```

- 로그 확인 
```
cf logs demo-cf-datasource --recent
```

## 로컬 환경 실행
- 설치
```
git clone https://github.com/CrossentCloud/PaaSXpert-Tutorial
cd PaaSXpert-Tutorial/demo-cf-datasource
```
- 실행
```
mvn install
java -jar target/demo-cf.datasource-0.0.1-SNAPSHOT.war paasxpert.demo.cf.datasource.CloudApplication
```


