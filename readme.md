# FC 버거킹 가계부
FC 버거킹의 회비 사용 내역 PDF 파일을 parsing 하는 parser

- 파싱 전 
<img src="https://postfiles.pstatic.net/MjAyMTA0MTJfMTcx/MDAxNjE4MjA5NDg0MjQy.LJgjjS5efkH-ii2ws9L9AYPbjqAAD-QLUQGbD5RV36wg.7uEDOE_VWFZ1vId4_WD6JsYjhdE-RdvGfUZsqDnAYjgg.PNG.securityst/SE-4b718a24-b6d9-46d1-89e3-9df30e2ae430.png?type=w580">
- 파싱 후
<img src="https://postfiles.pstatic.net/MjAyMTA0MTJfMTk2/MDAxNjE4MjEwMzYwMjIw.KiTmOz3gc5HKuxIHudXLYkjAQHpACfLYeLL9ktkVTqUg.G43MOpOlqFzmPtjlhcCWL0ygx-mplDNrVXGFgS2xn7gg.PNG.securityst/SE-151e493a-2294-462c-88fb-9e0a24bec06c.png?type=w580">

## 개념 정리
- 회비 사용 내역을 기록한 PDF 파일을 장부라고 함
- 잔액을 기록한 날을 결산일 이라 하고, 각 결산은 `=` 를 기준으로 나누어진다. (사용내역이 끝나고 `=`이 나타난다.)


## TODO
- [x] parser 에 text 를 넘겨주면 결산 일별로 구분
    - [x] 결산일 라인은 결산일+잔액 or 잔액으로 이루어져 있으므로 구분한다.
    - [x] 잔액은 직접 계산한 값을 사용한다. (일치하지 않으면 계산값 사용)
- [x] 결산 일별로 결산이 구분되면 각 결산 건들을 구분
- [ ] ~~결산 날짜가 null 인 경우 기본값을 줘야함 -> 정확한 정보를 위해 안주는게 나을 듯.~~
- [ ] 날짜에 연도는 작성되있지 않으므로 2020으로 시작해서 계산후 추가해줘야 함. 
- [x] ~~key-value 형태로 (결산, 잔액) 저장하고, 장부의 신뢰성을 위해 각 결산이 앞뒤 결산의 정보를 가져야 함~~    
  -> LinkedHashMap 을 사용해서 (결산, 잔액) 맵 구조와 순서를 보장.
- [x] 모든 정보를 장부에 담아서 반환



# Troubleshooting 
- https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save