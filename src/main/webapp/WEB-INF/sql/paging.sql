DECLARE
    DATA1 VARCHAR2(100) := 'HTML게시물';
    
-- 데이터를 1000개 추가한다.
BEGIN
    FOR I IN 7.. 1000
    LOOP
        INSERT INTO BOARD(BNO, MENU_ID, TITLE, CONTENT, WRITER, REGDATE, HIT)
        VALUES(I, 'MENU01', DATA1 || I, '내용', 'test', SYSDATE, 0);
    END LOOP;
    COMMIT;
END;
/

SELECT * FROM BOARD ORDER BY BNO DESC;

2. ROW_NUBER() 사용
SET TIMING ON
SELECT * FROM
(SELECT ROW_NUMBER() OVER(ORDER BY BNO DESC) RN,
    BNO, MENU_ID, TITLE, CONTENT, WRITER, REGDATE, HIT
FROM BOARD) T
WHERE T.RN BETWEEN 1 AND 10;


3. OFFSET .. FETCH .. 
SET TIMING ON
SELECT BNO,
    MENU_ID,
    TITLE,
    CONTENT,
    WRITER,
    TO_CHAR(REGDATE,'YYYY-MM-DD')REGDATE,
    HIT
FROM BOARD
ORDER BY BNO DESC
OFFSET 10 ROWS FETCH NEXT 20 ROW ONLY