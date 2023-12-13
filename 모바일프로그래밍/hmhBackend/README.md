## **KMU-Mobile-Programming : 뚜벅뚜벅**

---

2023 학년도 2 학기 모바일 프로그래밍 팀 프로젝트.

### 프로젝트 소개

---

계획 세우기 어렵고 지키기 힘든 뚜벅이*를 위한 약속 관리 서비스.

(뚜벅이* : 개인 소유의 자동차가 없어 대중교통 등을 이용하는 사람)

### 프로젝트 개발 기간

---

- 2023-09-04 ~ 2023-12-01

### Team HmH

---

- 목진협 (Backend)
- 남윤찬 (Backend)
- 김호 (Frontend)
- 조다운 (Frontend)

### 개발 환경

---

- Java JDK 11
- IDE: IntelliJ
- Framework: Spring Boot(2.7.16)
- Database: MySQL
- Build Tool: Gradle (8.0)

### 애플리케이션 버전

---

1.0

### 빌드시 유의사항

---

- AWS EC2 셸 접속 후  `./deploy` 실행

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6b194b87-1751-4fd7-8d74-d9aacf352c0a/dd19aec8-3589-4abe-8e11-37be60cedce4/Untitled.png)

```bash
# deploy.sh
REPO=/home/ubuntu
cd $REPO/hmhBackend/
echo "> Git Pull"
git pull
echo "> Start Build"
./gradlew build
echo "> Copy Build File"
cp ./build/libs/*.jar $REPO/
echo "> Check Currently running application PID"
CURRENT_PID=$(pgrep -f hmhBackend)
echo "$CURRENT_PID"
if [ -z $CURRENT_PID ]; then
    echo "> No applications are currently running and will not be shut down"
else
    echo "> kill $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi
echo "> Start New Deploy"
JAR_NAME=$(ls $REPO/ |grep 'hmhBackend-0.0.1-SNAPSHOT.jar' | tail -n 1)
echo "> JAR Name: $JAR_NAME"
nohup java -jar $REPO/$JAR_NAME &
```

### AWS 환경

---

**EC2 인스턴스 세부정보**

- Software : Ubuntu 20.04 LTS
- 인스턴스 유형 : t2.micro (1 vCPU, 1 Gib Memory)
- 키 페어 : hmhServerKey.pem
- 네트워크 정보 : default VPC(Network), Allow SSH traffic from
- 스토리지 구성 : 30GB-gp2 루트볼륨

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6b194b87-1751-4fd7-8d74-d9aacf352c0a/6d948ace-b873-40f1-865f-e53d20ea30c7/Untitled.png)

인스턴스 보안 그룹 정보 (보안 그룹 규칙 ID는 보안 상 마스킹하였습니다.)

- HTTPS 연결을 위한 인바운드 설정
- Spring Boot 포트를 위한 인바운드 설정
- HTTP 연결을 위한 인바운드 설정
- SSH 연결을 위한 인바운드 설정

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6b194b87-1751-4fd7-8d74-d9aacf352c0a/f7828391-fc98-429e-88ed-23acdae78cd5/Untitled.png)

스토리지 정보

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6b194b87-1751-4fd7-8d74-d9aacf352c0a/a49ab3c0-063d-4d97-b484-d6e9dfb1b35b/Untitled.png)

위와 같이 AWS EC2 인스턴스를 설정하여 가상의 컴퓨터를 제공받았습니다. 인스턴스는 AWS 콘솔에서의 monitoring과 로컬에서 관리하고 있습니다.

**제공받은 인스턴스 셸 접속**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/6b194b87-1751-4fd7-8d74-d9aacf352c0a/829c982d-5d3d-4161-b413-9cc928992f8d/Untitled.png)

**AWS RDS 데이터베이스 세부 정보**

클라우드 서버 내에서 작동하는 관계형 데이터베이스를 구축하기 위해 AWS RDS를 사용하였습니다.

RDS 데이터베이스 세부정보

- 엔진 유형 : MySQL (MySQL 8.0.33)
- 인스턴스 구성 :  db.t2.micro(1 vCPU, 1 GiB RAM) - DB를 연결할 EC2와 동일한 설정
- 스토리지 : 범용 SSD(gp2)-20GiB
- 연결 : default VPC, 서브넷, 퍼블릭 엑세스 허용, 인증기관(rds-ca-2019), 데이터베이스 인증(암호)
