# JP_term_project
Check the master branch

# World’s Most Difficult Block Avoidance Game

“세상에서 가장 어려운 게임” 플래시 게임을 Java Swing + Socket 통신으로 재구현한 프로젝트이다.  
플레이어는 장애물을 피해 맵의 끝까지 도달해야 하며, 클리어 시 리더보드에 점수가 기록됩니다.

## Program Activation Flow
![Image](https://github.com/user-attachments/assets/2d470859-e5e9-40e2-9314-b910146e0acd)
![Image](https://github.com/user-attachments/assets/88661113-207b-4a25-bda6-9c74bfb4b9d1)

## 주요 기능
- **맵 로딩**: 텍스트 기반 맵 파일을 읽어들여 타일(이동 가능, 충돌, 안전지대, 종료지점)로 변환
![Image](https://github.com/user-attachments/assets/72ff46c7-7fdb-45e2-9cbc-a8742cea45de)
- **플레이어·적 이동**: `KeyHandler`로 속도 조절, `GamePanel`에서 스레드 기반 애니메이션  
- **충돌 감지**: 벽·적·종료지점 충돌 처리 (`CollisionDetector`, `EnemyCollision`)
![Image](https://github.com/user-attachments/assets/b5b94912-1205-46e1-a364-9f7aa21dddd5)
- **리더보드**:  
  - `Server`가 `result.txt`에 점수(죽은 횟수)·유저명 저장  
  - `Client`가 서버와 소켓 연결, 로컬에 점수 불러와 정렬 및 출력  
- **게임 상태 관리**: 타이틀, 플레이, 리더보드, 엔드스테이트 전환

## 사용 기술
- **Java SE**: Swing / AWT UI, Thread, Runnable
- **Socket 통신**: Client–Server 데이터 교환
- **File I/O**: result.txt 읽기/쓰기
