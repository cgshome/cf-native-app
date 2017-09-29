# 로컬 Docker 환경 구성 ##

### DockerMachine 설치 ###
- 윈도우 환경에서 필요(리눅스 환경은 Skip)
- Docker machine 설치

``` bash
curl -L https://github.com/docker/machine/releases/download/v0.6.0/docker-machine-Windows-x86_64.exe > $HOME/bin/docker-machine.exe
chmod +x $HOME/bin/docker-machine.exe
$HOME/bin/docker-machine.exe create --driver virtualbox test
$HOME/bin/docker-machine.exe ls
docker-machine ip test  // Get the host IP
$HOME/bin/docker-machine.exe env test
eval $($HOME/bin/docker-machine.exe env test)
```

- Docker machine Start/Stop

``` bash
docker-machine stop test
docker-machine start test
```

### Docker 설치 ###
- (Ubuntu 환경) Docker 설치

```
sudo apt-get install docker
```

- Conainter 실행 테스트

``` bash
docker run busybox echo hello world
```

### Docker Service 실행 ###

- redis 

``` bash
docker run -p 6379:6379 --name redis -d redis 
nc 192.168.99.100:6379  # Windows 포트 확인
nc 127.0.0.1:6379  # Ubuntu 포트 확인
docker run -it --link redis:redis --rm redis sh -c 'exec redis-cli -h "192.168.99.100" -p "6379"'
192.168.99.100:6379> keys *
(empty list or set)
docker run -it --link redis:redis --rm redis sh -c 'exec  redis-cli'
docker run -it --link redis:redis --rm redis sh -c 'exec  redis-cli keys "*"'
docker run -it --link redis:redis --rm redis sh -c 'exec  redis-cli keys "*" | xargs redis-cli del'
```

- mysql

``` bash
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=password -d mysql --character-set-server=utf8 --collation-server=utf8_general_ci
nc 192.168.99.100:3306  # Windows 포트 확인
nc 127.0.0.1:3306  # Ubuntu 포트 확인
nc 192.168.59.103:3306 # Mac 포트 확인
# create database
docker run -it --link mysql:mysql --rm mysql sh -c 'exec mysql -h"192.168.99.100" -P"3306" -uroot -p"password" -e "create database PaasXpertPortal"' # Windows mysql cli 접속
docker run -it --link mysql:mysql --rm mysql sh -c 'exec mysql -h"127.0.0.1" -P"3306" -uroot -p"password" -e "create database PaasXpertPortal"' # Ubuntu mysql cli 접속
docker run -it --link mysql:mysql --rm mysql sh -c 'exec mysql -h"192.168.59.103" -P"3306" -uroot -p"password" -e "create database PaasXpertPortal"' #  Mac mysql cli 접속
# mysql console access database
docker run -it --link mysql:mysql --rm mysql sh -c 'mysql -h"192.168.99.100" -P"3306" -uroot -p"password"'
# charset check
mysql> status
mysql> show variables like 'c%';
```
