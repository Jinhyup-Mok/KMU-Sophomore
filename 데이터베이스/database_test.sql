# date_format(birth_date,get_format(date, "ISO or USA or ...)) 로 해당 지역의 날짜 표기로 포멧!
# coalesce(column, ) null일때 표시할 것 설정
# concat(a, "", b) 문자열 합치기
# count 집계함수


# 1번
-- SELECT TEAM_ID, MAX(HEIGHT) 최대신장, MIN(HEIGHT) 최소신장, ROUND(AVG(HEIGHT),2) 평균신장 # 소수점 몇째자리 수까지 조심! -> ROUND(컬럼, 표시할 자리수)
-- FROM PLAYER
-- GROUP BY TEAM_ID 
-- ORDER BY TEAM_ID

# 3번
-- SELECT PLAYER_ID, COALESCE(NICKNAME, "****") NICKNAME, 
-- 	CASE WHEN HEIGHT IS NOT NULL AND WEIGHT IS NOT NULL THEN HEIGHT + WEIGHT
-- 		 WHEN HEIGHT IS NULL THEN COALESCE(WEIGHT, 0)
--          WHEN WEIGHT IS NULL THEN COALESCE(HEIGHT, 0)
--          WHEN HEIGHT IS NULL AND WEIGHT IS NULL THEN 0
--          END AS SUM
-- 	# CASE WHEN 조건 THEN (when 조건충족시 실행) ELSE (when조건 충족X시 실행)
-- 	#      WHEN ... THEN ...
--  #	   WHEN ... THEN ...
-- 	#	   END ALIAS(별칭)
--  # 함수 COALESCE(A(COLUMN), B(앞 COLUMN값이 NULL일때 표시할 값))
-- 	# -> 만약 A = NULL 이라면 B 출력
--  # -> 만약 A = NOT NULL 이라면 A 출력
-- FROM PLAYER
-- ORDER BY PLAYER_ID

# 4번
-- SELECT STADIUM_NAME, 
-- 	CASE WHEN SEAT_COUNT >= 50000 THEN "대"
-- 		 WHEN SEAT_COUNT >= 30000 THEN "중"
-- 		 WHEN SEAT_COUNT < 30000 THEN "소"
--          # WHEN SEAT_COUNT < 50000 AND  SEAT_COUNT >= 30000 THEN "중"
--          END AS SEAT_COUNT
-- FROM STADIUM
-- ORDER BY STADIUM_NAME

# 5번
-- SELECT T.TEAM_NAME, COUNT(*) 팀인원수, AVG(HEIGHT) 팀평균키
-- FROM PLAYER AS P
-- JOIN TEAM AS T ON P.TEAM_ID = T.TEAM_ID
-- # WHERE AVG(HEIGHT) <= 180 -> 어떤 그룹을 기준으로 한 평균값인지 모름 -> GROUP BY 를 통해 그룹 설정해야..
-- GROUP BY P.TEAM_ID HAVING COUNT(P.PLAYER_ID) >= 46 AND AVG(HEIGHT) <= 180
-- # TEAM_ID 항목을 기준으로 -> 팀으로 구분함 -> 팀의 인원수, 팀의 평균키
-- ORDER BY T.TEAM_NAME

# 6번
-- SELECT COUNT(PLAYER_ID)
-- # COUNT(COLUMN) 총갯수 출력 -> PLAYER_ID 항목의 행 개수 출력
-- # COUNT(COLUMN) 만약 COLUMN이 PK라면 COUNT(*)과 똑같다!!
-- FROM PLAYER
-- WHERE NATION IS NOT NULL AND HEIGHT <= 175

# 7번
-- SELECT COUNT(*) 인원수, CASE WHEN NATION IS NULL THEN "대한민국" ELSE NATION END AS 출신국
-- FROM PLAYER
-- GROUP BY NATION
-- ORDER BY 인원수 DESC, 출신국 ASC

# 8번
-- SELECT PLAYER_ID, PLAYER_NAME, HEIGHT
-- FROM PLAYER
-- WHERE PLAYER_NAME LIKE "김%" AND HEIGHT IN (
-- 	SELECT MAX(HEIGHT) AS HEIGHT
--     FROM PLAYER
--     WHERE PLAYER_NAME LIKE "김%"
--     # GROUP BY HEIGHT -> 키를 기준으로 테이블이 나눠지기 때문에 MAX(HEIGTH)이 의미가 없어지고, HEIGHT항목의 모든 값들이 출력된다!! 조심!
--     # GROUP BY 를 왜 쓰면 안될까?
-- )
-- ORDER BY PLAYER_NAME

# 9번
-- SELECT STADIUM_NAME, SEAT_COUNT, TEAM_NAME
-- FROM STADIUM AS S
-- JOIN TEAM AS T ON S.STADIUM_ID = T.STADIUM_ID
-- # GROUP BY 함부로 쓰지 말자!
-- ORDER BY STADIUM_NAME

# 10번
-- SELECT PLAYER_NAME, HEIGHT
-- FROM PLAYER
-- WHERE HEIGHT < (
-- 	# 서브쿼리 를 잘 써보자!! 서브쿼리로 <, =, > 등 이용가능!
-- 	   SELECT AVG(HEIGHT)
--     FROM PLAYER
-- )
-- ORDER BY HEIGHT DESC, PLAYER_NAME ASC

# 11번

-- 단순 서브쿼리
-- SELECT TEAM_ID, PLAYER_NAME, HEIGHT
-- FROM PLAYER
-- WHERE (TEAM_ID, HEIGHT) IN (
-- 	SELECT TEAM_ID, MIN(HEIGHT)
--     FROM PLAYER
--     GROUP BY TEAM_ID
-- )
-- ORDER BY TEAM_ID, PLAYER_NAME

#########################################

-- WITH AS 절 사용
-- WITH TMP AS (
-- 	SELECT TEAM_ID, MIN(HEIGHT) AS H
--     FROM PLAYER
--     GROUP BY TEAM_ID
-- )
-- SELECT TEAM_ID, PLAYER_NAME, HEIGHT
-- FROM TMP JOIN PLAYER USING(TEAM_ID)
-- WHERE TMP.H = PLAYER.HEIGHT
-- ORDER BY TEAM_ID, PLAYER_NAME

# 12번
-- SELECT TEAM_ID, TEAM_NAME
-- FROM PLAYER AS P
-- JOIN TEAM AS T USING(TEAM_ID)
-- WHERE PLAYER_NAME = "김종민"
-- ORDER BY TEAM_ID

# 13번
-- WITH TMP AS (
-- 	SELECT TEAM_ID, SUM(CASE WHEN NATION IS NOT NULL THEN 1 ELSE 0 END) AS 외국인수 # 합을 하나의 column으로!
--     # 외국인수가 없는 팀 -> SUM(CASE WHEN TEHN ELSE END)로 외국인 있을땐 +1, 한국인 일때 +0 -> 총 합이 0이면 그 팀에는 외국인이 없다!!
--     # 조건문을 자유자재로 쓸 수 있어야 한다 !!!
--     FROM PLAYER
--     GROUP BY TEAM_ID
-- )
-- SELECT TEAM_NAME
-- FROM TMP
-- JOIN TEAM USING(TEAM_ID)
-- WHERE TMP.외국인수 = 0
-- ORDER BY TEAM_NAME

# 14번
-- SELECT TEAM_ID, SUM(CASE WHEN NATION IS NULL THEN 1 ELSE 0 END) AS 내국인수, SUM(CASE WHEN NATION IS NOT NULL THEN 1 ELSE 0 END) AS 외국인수, COUNT(PLAYER_ID) 총인원수
-- FROM PLAYER
-- GROUP BY TEAM_ID
-- ORDER BY TEAM_ID

# 15번
-- WITH TMP AS (
-- 	SELECT TEAM_ID, SUM(CASE WHEN HOME_SCORE < AWAY_SCORE THEN 1 ELSE 0 END) 패배횟수
--     FROM SCHEDULE SC 
--     JOIN TEAM T ON SC.STADIUM_ID = T.STADIUM_ID AND T.TEAM_ID = SC.HOMETEAM_ID AND GUBUN = 'Y'
--     # SCHEDULE과 TEAM 테이블을 조인하여 홈팀이 되는 팀과 스케줄의 경기장 ID가 일치하며, GUBUN 값이 'Y'인 경우를 선택
--     GROUP BY TEAM_ID
-- )
-- SELECT TMP.TEAM_ID, TEAM_NAME
-- FROM TMP JOIN TEAM USING(TEAM_ID)
-- WHERE TMP.패배횟수 = 0
-- ORDER BY TEAM_ID

# 16번
-- WITH TMP AS (
-- 	SELECT TEAM_ID, SUM(CASE WHEN POSITION = 'GK' THEN 1 ELSE 0 END) AS 골키퍼인원수
--     FROM PLAYER
--     GROUP BY TEAM_ID
-- )
-- SELECT TMP.TEAM_ID, TMP.골키퍼인원수, COUNT(PLAYER_ID) 총인원수
-- FROM TMP JOIN PLAYER USING(TEAM_ID)
-- WHERE TMP.골키퍼인원수 >= 4
-- GROUP BY TEAM_ID
-- ORDER BY 총인원수

# 17번
-- SELECT STADIUM_NAME, SEAT_COUNT, T1.TEAM_NAME AS 홈팀명, T2.TEAM_NAME AS 원정팀, COALESCE(CONCAT(HOME_SCORE, ":", AWAY_SCORE), NULL) AS 경기점수
-- FROM STADIUM AS S JOIN SCHEDULE AS SC USING(STADIUM_ID)
-- JOIN TEAM AS T1 ON T1.TEAM_ID = SC.HOMETEAM_ID
-- JOIN TEAM AS T2 ON T2.TEAM_ID = SC.AWAYTEAM_ID
-- # 이런식으로 같은 테이블과 두번의 조인을 통해서, 각 조인마다 다른 값을 뽑아올 수 있다 (ex: T1으로는 홈팀, T2로는 원정팀)
-- ORDER BY STADIUM_NAME, SCHE_DATE

# 18번
-- SELECT STADIUM_NAME, SEAT_COUNT, TEAM_NAME AS 홈팀명, COUNT(PLAYER_ID) AS 팀인원수
-- FROM STADIUM AS ST LEFT JOIN TEAM AS T USING(STADIUM_ID) LEFT JOIN PLAYER AS P ON (T.TEAM_ID = P.TEAM_ID)
-- # LEFT JOIN은 NULL도 표시!!
-- GROUP BY STADIUM_NAME, SEAT_COUNT, TEAM_NAME
-- # 위 3가지를 GROUP BY 해야 COUNT(PLAYER_ID)가 나옴!
-- ORDER BY STADIUM_NAME

# 19번
-- SELECT TEAM_ID, TEAM_NAME, MAX(SCHE_DATE)
-- FROM TEAM AS T
-- JOIN SCHEDULE AS SC
-- ON T.TEAM_ID IN (SC.HOMETEAM_ID, SC.AWAYTEAM_ID) AND GUBUN = 'Y'
-- GROUP BY T.TEAM_ID HAVING COUNT(SCHE_DATE) != 0
-- ORDER BY TEAM_ID

# 20번
-- 43. 각 팀의 승리 게임수, 무승부 게임수, 패배 게임수, 그리고 팀 의 전체 게임수를 검색하시오. 단, 점수가 없는 경기는 실제 진행되지 않았으므로 게임수에서 제외하시오
select team_id, sum(WIN), sum(TIE), sum(LOSE), sum(WIN) + sum(TIE) + sum(LOSE) TOTAL
from (
select hometeam_id as team_id, case when home_score > away_score then 1 else 0 end as WIN
, case when home_score < away_score then 1 else 0 end as LOSE
, case when home_score = away_score then 1 else 0 end as TIE
from schedule
union all
select awayteam_id, case when home_score < away_score then 1 else 0 end as WIN
, case when home_score > away_score then 1 else 0 end as LOSE
, case when home_score = away_score then 1 else 0 end as TIE
from schedule
) as a
group by team_id
order by 1

###############################################################################
#예제 문제 start! 
-- 42. 홈에서 진 적이 없는 팀을 검색하시오. 단, 홈 경기가 없던 팀 은 제외하시오.
with tmp as (
	select team_id, sum(case when home_score < away_score then 1 else 0 end) as 진경기
    from schedule s join team t on s.stadium_id = t.stadium_id and s.hometeam_id = t.team_id and gubun = 'Y'
    # on절에 or 와 and 사용가능하다
    # 여기서는 team_id = hometeam_id으로 조인하여 홈팀만 경기만 픽 -> 홈 경기가 없던 팀을 제외해야 하니 홈구장인 stadium_id도 join, 그리고 and gubun = 'Y'
    group by team_id
)
select t.*
from team t join tmp using(team_id)
where tmp.진경기 = 0

-- 41. GK가 4인 이상 있는 팀의 팀명과 GK 선수의 인원수, 팀 전 체 선수의 인원수를 검색하시오
with tmp as (
	select team_id, count(*) GK
    from player
    where position = 'GK'
    group by team_id
)
select team_name, GK, count(p.player_id) 총인원수
from team t join tmp using(team_id)
	join player p on p.team_id = t.team_id
where GK >= 4
group by team_name, GK
order by 3

# 풀이 2 -> 만약 team_id를 출력하라고 할때 이걸로!
-- WITH TMP AS (
-- 	SELECT TEAM_ID, SUM(CASE WHEN POSITION = 'GK' THEN 1 ELSE 0 END) AS 골키퍼인원수
--     FROM PLAYER
--     GROUP BY TEAM_ID
-- )
-- SELECT TMP.TEAM_ID, TMP.골키퍼인원수, COUNT(PLAYER_ID) 총인원수
-- FROM TMP JOIN PLAYER USING(TEAM_ID)
-- WHERE TMP.골키퍼인원수 >= 4
-- GROUP BY TEAM_ID
-- ORDER BY TEAM_ID

-- 40. 각 팀에 대해 최고 점수차로 이긴 게임의 상대팀 아이디, 게 임이 여린 날짜와 경기장 아이디, 점수차 및 경기 점수를 검색하시오. 단, 최고 점수차로 이긴 게임이 2개 이상이면 모두 나열하시오. 또한 경기 점수는 '이긴팀 점수:진팀 점수'로 표현하시오.  (★★★)
select tmp.team_id, 
if(tmp.team_id=s.hometeam_id, s.awayteam_id, s.hometeam_id) as 'lose_team',
s.sche_date, s.stadium_id, max_score, concat(if(tmp.team_id=s.hometeam_id, s.home_score, s.away_score), ':', if(tmp.team_id=s.hometeam_id, s.away_score, s.home_score)) as '경기 점수'
from schedule s, 
  (select t.team_id, if(t.team_id=s.hometeam_id, max(s.home_score-s.away_score), max(s.away_score-s.home_score)) as 'max_score'  
	from team t, schedule s
	where t.team_id = s.hometeam_id or t.team_id = s.awayteam_id
	group by team_id)  as tmp
where (s.hometeam_id = tmp.team_id and (s.home_score-s.away_score) = max_score) or (s.awayteam_id = tmp.team_id and (s.away_score-s.home_score) = max_score)
order by tmp.team_id;

-- 1. team_id가 K07인 선수의 아이디, 이름, 백넘버를 출력하시오
SELECT TEAM_ID, PLAYER_NAME, BACK_NO
FROM PLAYER
WHERE TEAM_ID = 'K07'

-- 2. 브라질출신 MF와 러시아출신 FW인 선수의 이름, 팀 아이 디, 포지션, 국가를 출력하시오.
SELECT PLAYER_NAME, TEAM_ID, POSITION, NATION
FROM PLAYER
WHERE (NATION = "브라질" AND POSITION = "MF") OR (NATION = "러시아" AND POSITION = "FW")

-- 3. 장씨 선수의 정보를 출력하세요
SELECT PLAYER_NAME, TEAM_ID, POSITION, NATION
FROM PLAYER
WHERE (NATION = "브라질" AND POSITION = "MF") OR (NATION = "러시아" AND POSITION = "FW")

-- 4. 포지션이 존재하지 않는 선수의 이름과 포지션, 팀 아이디를 출력하세요.
SELECT PLAYER_NAME, POSITION, TEAM_ID
FROM PLAYER
WHERE POSITION IS NULL

-- 5. 선수의 이름, 출생년도, 출생월, 출생일을 출력하시오 (★)
SELECT PLAYER_NAME, YEAR(BIRTH_DATE) 출생년도, MONTH(BIRTH_DATE) 출생월, DAY(BIRTH_DATE) 출생일
FROM PLAYER

-- 6. 선수의 이름과 나이를 출력하시오 (★)  TIMESTAMPDIFF(YEAR, DATE, DATE(NOW())) + 1 : 그냥 나이, 만은 +1 빼라
SELECT PLAYER_NAME, TIMESTAMPDIFF(YEAR, BIRTH_DATE, DATE(NOW())) + 1 AS 나이
FROM PLAYER

-- 7. K08팀의 이름과 포지션, 키를 출력하시오. 단 포지션과 키 의 정보가 없을 때는 각각 *****, 0 으로 출력하시오
SELECT PLAYER_NAME, COALESCE(POSITION, "*****") 포지션, COALESCE(HEIGHT, 0) 키
FROM PLAYER
WHERE TEAM_ID = 'K08'

-- 8. 선수의 이름과 영어이름을 출력하시오. 영어이름이 없으면 닉네임을 출력하시오
SELECT PLAYER_NAME, COALESCE(E_PLAYER_NAME, NICKNAME)
FROM PLAYER

-- 9. 동명이인인 선수이름을 출력하시오
SELECT PLAYER_NAME
FROM PLAYER
GROUP BY PLAYER_NAME HAVING COUNT(*) >= 2

-- 10. 평균키가 180 이상인 포지션을 출력하시오.
SELECT POSITION
FROM PLAYER
GROUP BY POSITION HAVING AVG(HEIGHT) >= 180

-- 11. 팀별로 각각의 생월에 대한 선수의 평균 키를 구하시오
SELECT TEAM_ID, MONTH(BIRTH_DATE) 생월, ROUND(AVG(HEIGHT),2) 평균키
FROM PLAYER
GROUP BY TEAM_ID, 생월
ORDER BY 생월

-- 12. 팀별로 각 포지션에 대한 인원수, 그리고 팀의 전체 인원수를 구하시오. 단 데이터가 없는 경우는 0으로 표시한다.
SELECT TEAM_ID, COALESCE(COUNT(PLAYER_ID)) 팀인원수
FROM PLAYER
GROUP BY TEAM_ID
UNION
SELECT COALESCE(POSITION,0), COALESCE(COUNT(PLAYER_ID)) AS 포지션인원수
FROM PLAYER
GROUP BY POSITION

-- 13. 가장 규모가 큰 3개의 경기장의 아이디, 이름, 좌석수를 출력 하시오.
SELECT STADIUM_ID, STADIUM_NAME, SEAT_COUNT
FROM STADIUM
ORDER BY SEAT_COUNT DESC
LIMIT 3;
# 최상위 몇개, 최하위 몇개 -> ORDER BY 로 정렬후 LIMIT 이용!!

-- 14. K02 혹은 K07 팀 선수들을 검색 (UNION 사용)
SELECT *
FROM PLAYER
WHERE TEAM_ID = "K02"
UNION
SELECT *
FROM PLAYER
WHERE TEAM_ID = "K07"
# union : default로 distinct, all 설정해야..and
# SELECT FROM UNION SELECT FROM 으로 합집합 구하기

-- 15. 소속이 K02 팀이면서 포지션이 GK인 선수들을 검색
SELECT *
FROM PLAYER
WHERE TEAM_ID = "K02" AND POSITION = "GK"

-- 16. K02팀이면서 포지션이 MF가 아닌 선수들을 검색
SELECT *
FROM PLAYER
WHERE TEAM_ID = "K02" AND POSITION != "MF"

-- 17. 소속이 K02팀이면서 포지션이 MF가 아닌 선수들을 검색
SELECT *
FROM PLAYER
WHERE TEAM_ID="K02" AND POSITION != "MF"

-- 18. 선수들의 이름, 백넘버, 소속 팀명 및 팀 연고지를 검색
SELECT PLAYER_NAME, BACK_NO, P.TEAM_ID, T.REGION_NAME
FROM PLAYER P JOIN TEAM T USING(TEAM_ID)

-- 19. 포지션이 'GK'인 선수들의 이름, 백넘버, 소속팀명 및 팀 연 고지를 검색, 단 백넘버의 오름차순으로 출력
SELECT PLAYER_NAME, BACK_NO, TEAM_NAME, REGION_NAME
FROM PLAYER JOIN TEAM USING(TEAM_ID)
WHERE POSITION = "GK"
ORDER BY BACK_NO

-- 20. 선수 이름, 소속 팀, 그 팀의 전용구장 정보를 같이 출력
SELECT PLAYER_NAME, TEAM_NAME, S.*
# 이런식으로 조인한 테이블의 alias로 T.* 로 조인한 테이블에 대한 모든 정보를 출력할 수 있다!
FROM PLAYER P JOIN TEAM T ON P.TEAM_ID = T.TEAM_ID
JOIN STADIUM S ON S.STADIUM_ID = T.STADIUM_ID

-- 21. GK 포지션의 선수 마다 팀 연고지명, 팀명, 구장명을 출력
SELECT POSITION, PLAYER_ID, PLAYER_NAME, REGION_NAME, TEAM_NAME, STADIUM_NAME
FROM PLAYER P JOIN TEAM T USING(TEAM_ID)
	JOIN STADIUM S ON S.STADIUM_ID = T.STADIUM_ID
WHERE P.POSITION = "GK"

-- 22. 홈팀이 3점 이상 차이로 승리한 경기의 경기장 이름, 경기 일 정, 홈팀 이름과 원정팀 이름 정보를 출력
SELECT s.STADIUM_NAME, sc.SCHE_DATE, t1.TEAM_NAME 홈팀, T2.TEAM_NAME 원정팀, CONCAT(sc.home_score, ":", sc.away_score) 경기점수
FROM SCHEDULE AS sc JOIN stadium AS s using(STADIUM_ID)
JOIN TEAM AS t1 ON t1.TEAM_ID = sc.HOMETEAM_ID
JOIN TEAM AS t2 ON t2.TEAM_ID = sc.AWAYTEAM_ID
where sc.home_score - sc.away_score >= 3

-- 23. 스케줄 테이블에서 경기장 이름, 홈팀 이름, 어웨이팀 이름을 출력하시오.
select s.stadium_name, t1.team_name, t2.team_name
from schedule sc join stadium s using(stadium_id)
join team t1 on t1.team_id = sc.hometeam_id
join team t2 on t2.team_id = sc.awayteam_id

-- 24. 날짜별 경기수를 출력하시오. 단 누락된 날짜가 없게 하시오
select sche_date, coalesce(count(*), 0)
from schedule
group by sche_date
order by sche_date

-- 25. 선수들의 평균 키보다 작은 선수들의 이름, 포지션, 백넘버를 출력
select player_name, position, back_no, height
from player
where height <= (select avg(height) from player) # 이거 잘 써보자!

-- 26. 정현수 선수의 소속팀의 연고지명, 팀명, 영문팀명을 출력
select team_id, region_name, team_name, e_team_name
from player p join team t using(team_id)
where player_name = "정현수"

-- 27. 각 팀에서 제일 키가 작은 선수들의 팀아이디, 선수명, 포지 션, 백넘버, 키를 출력
select team_id, player_name, position, back_no, height
from player
where (height, team_id) in (
	select min(height), team_id
    from player
    group by team_id
)

-- 28. 포지션이 GK인 선수들을 검색(연관 서브쿼리 사용)  (★)
select *
from player
where position = "GK"

-- 29. 브라질 혹은 러시아 출신 선수가 있는 팀을검색(연관 서브커 리 사용), 팀아이디, 팀명을 출력
select team_id, team_name
from team
where team_id in (
	select team_id
    from player
    where nation in ("브라질", "러시아")
)

-- 30. 20120501부터 20120502 사이에 경기가 열렸던 경기장을 조회(연관 서브쿼리 사용), 경기장아이디, 경기장명을 출력 (★)
select stadium_id, stadium_name, sche_date
from stadium join schedule using(stadium_id)
where sche_date in ("20120501", "20120502")

-- 31. 각 팀에서 제일 키가 큰 선수들의 팀 아이디, 이름, 키를 출력
select team_id, team_name, player_id, player_name, height
from team join player using(team_id)
where (team_id, height) in (
	select team_id, max(height)
    from player
    group by team_id
)

-- 32. 소속 팀의 평균 키보다 작은 선수들의 팀 아이디, 이름, 포지 션, 백넘버, 키를 출력
with tmp as (
	select team_id, avg(height) 평균키
    from player
    group by team_id
) 
# with as 절에서 임시테이블을 만들어 각 팀별로 평균 키를 저장해놓는다!!!!
select team_id, player_name, position, back_no, height
from player join tmp using(team_id)
where height <= tmp.평균키
order by team_id, player_name
# with as 에서 정의된 column은 밖의 테이블에서 alias가 아닌 진짜 column으로 쓰인다.

# 풀이 2번
-- select team_id, player_name, position, back_no, height
-- from player p1
-- where height < (select avg(height) from player p2 where p1.team_id = p2.team_id)
-- order by team_id, height, player_name;

-- 33. 팀 아이디, 선수명, 키, 소속 팀의 평균키를 출력
with tmp as (
	select team_id, round(avg(height),2) 평균키
    from player
    group by team_id
)
select team_id, player_name, height, 평균키
from player join tmp using(team_id)
order by team_id

-- 34. 팀 아이디, 팀명, 팀 인원수를 출력
select team_id, team_name, count(*) 인원수
from player join team using(team_id)
group by team_id
# 풀이 2번
-- select team_id, team_name, (select count(*) from player p where p.team_id = t.team_id) as '팀 인원수'
-- from team t;

-- 35. 팀 아이디, 팀명, 각 팀의 마지막 경기가 진행된 날짜를 출력 (★)
with tmp as (
	select team_id, max(sche_date) 마지막경기
    from schedule s join team t on s.hometeam_id = t.team_id or s.awayteam_id = t.team_id
    # 만약 진행된 경기, 경기 진행하지 않은 경기일자를 제외할 시에는 -> where gubun = "Y"  써줘야 한다!!!
    # stadium_id로 묶으면 안된다! 홈팀과 원정팀의 구분이 안된다!!
    group by team_id
)
select team_id, team_name, 마지막경기 "마지막 경기 일자"
from team left join tmp using(team_id) # null을 표시해야한다면!!! -> left join
order by team_id # 마지막 경기가 null값일때도 출력하라고 하면 -> left join으로 !!

# 풀이 2번 -> 웬만하면 이걸로!!
-- select team_id, team_name, (select max(sche_date) from schedule s where s.hometeam_id = t.team_id or s.awayteam_id = t.team_id) as '마지막 경기 일자'
-- from team t;

-- 36. 포지션이 MF인 선수들의 소속팀명 및 선수이름과 백넘버를 출력
select team_name, player_name, position, back_no
from player join team using(team_id)
where position = "MF"
order by team_name

-- 37. 키가 제일 큰 5명의 선수들의 이름, 포지션, 백넘버를 출력
select player_name, position, back_no
from player
# 만약 height도 select 한다면 -> where height IS NOT NULL # 키나 몸무게 측정안된 사람들도 있다!!!! -> IS NOT NULL 필요!
order by height desc
limit 5

-- 38. K02 팀의 평균키보다 평균키가 작은 팀의 이름과 해당 팀의 평균키를 출력
with tmp as (
	select team_id, round(avg(height),2) 평균키
    from player
    group by team_id
)
select team_name, tmp.평균키
from tmp join team using(team_id)
where tmp.평균키 <= (select avg(height) from player where team_id = "K02")
order by tmp.평균키

# 풀이 2번
-- select t.team_name, round(avg(height), 2)
-- from team t, player p
-- where t.team_id = p.team_id
-- group by team_name
-- having avg(height) < (select avg(height) from player where team_id='K02');

-- 39. 각 팀의 최종 경기일을 검색하시오. 단, 경기가 없던 팀은 제외하시오.  (★)
with tmp as (
	select team_id, gubun, max(sche_date) 최종경기
    from schedule s join team t on s.hometeam_id = t.team_id or s.awayteam_id = t.team_id
    where gubun = 'Y' # 경기가 없었던 팀 제외
    group by team_id
)
select team_id, team_name, tmp.최종경기
from team join tmp using(team_id)
order by team_id


# 연습문제 8번 -> 2012년 4월 경기 횟수 날짜별로 검색하기
-- 코드를 입력하세요
WITH RECURSIVE DATES (DATE) AS
(
    SELECT CAST(MIN(SCHE_DATE) AS DATE)
    FROM KL_SCHEDULE
    UNION
    SELECT DATE + INTERVAL 1 DAY  # 더하기 ADDDATE 와 동일 역할, 숫자 + 단위
    FROM DATES
    WHERE DATE + INTERVAL 1 DAY <= '2012-04-30'
)
SELECT DATE_FORMAT(DATES.DATE, "%Y-%m-%d") 날짜, COALESCE(COUNT(SCHE_DATE),0) 경기횟수
FROM DATES LEFT JOIN KL_SCHEDULE ON DATES.DATE = KL_SCHEDULE.SCHE_DATE
GROUP BY DATES.DATE HAVING DATE_FORMAT(DATES.DATE, "%Y-%m") = "2012-04"
ORDER BY DATES.DATE



