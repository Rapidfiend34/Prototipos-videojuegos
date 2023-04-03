; ==============================================================================
; GAME VARIABLES
; ==============================================================================

; --- BUTTON RELATED VARIABLES -------------------------------------------------
TOUCHED     DS.W    1
BUTCOL1     DC.L    0
BUTCOL2     DC.L    0
BUTCOL3     DC.L    0

; --- SHOOT RELATED VARIABLES --------------------------------------------------
SHOTPOSX     DS.W    1                       ; SHOOT X COORDINATE
SHOTPOSY     DS.W    1                       ; SHOOT Y COORDINATE
SHOTVELY     DS.W    1                       ; SHOOT Y VELOCITY
SHOTIMER     DC.W    10                      ; SHOOT TIMER

; --- MARCIANOS RELATED VARIABLES-----------------------------------------------
MARPOSX     DS.W    1                       ; MARCIANOS X COORDINATE
MARPOSY     DS.W    1                       ; MARCIANOS Y COORDINATE
MARSPEDX    DC.W    1                       ; MARCIANOS VELOCITY
SUMMAR      DC.W    $4F                     ; DISTANCE BETWEEN MARCIANOS 
NUMMAR      DC.W    7                       ; MARCIANOS IN A ROW (0-7)
MAXMAR      DC.W    0                       ; MARCIANOS MAXIMUM NUMBER
MARTIMER    DC.W    0                       ; MARCIANOS TIME BETWEEN Y MOVEMENTS
BORDCOLL    DC.W    0                       ; MARCIANOS SCREEN BORDER COLLISION
ROWTIMER    DC.W    $A0                     ; TIME BETWEEN ROW GENERATIONS
RDMCOUNT    DC.W    0                       ; MARCIANOS RANDOM COUNTER

; --- SHOOTMAR MARCIANO RELATED VARIABLES---------------------------------------
MAXSHTMA    DC.W    0

; --- SHIP RELATED VARIABLES ---------------------------------------------------
SHIPPOSY    DS.W    1                       ; SHIP Y COORDINATE
SHIPPOSX    DS.W    1                       ; SHIP X COORDINATE
SHIPVEL     DC.W    0                       ; SHIP VELOCITY
LENGLIFE    DC.W    0                       ; SHIP'S LIFE BAR LENGTH
LIFES       DC.W    3                       ; NUMBER OF INITIAL LIFES

; --- SCOREBOARD RELATED VARIABLES ---------------------------------------------
SCOOUT      DS.W    1                       ; SCORE (50*MARCIANOS KILLED)

; --- STATE RELATED VARIABLES --------------------------------------------------
STACUR      DS.W    1                       ; CURRENT STATE
STANEXT     DS.W    1                       ; NEXT STATE

;--- MOUSE REALTED VARIABLES ---------------------------------------------------
BUTTON_R    DS.W    1                       ; MOUSE RIGHT BUTTON
BUTTON_L    DS.W    1                       ; MOUSE LEFT BUTTON
MOUSEX      DS.W    1                       ; MOUSE X COORDINATE
MOUSEY      DS.W    1                       ; MOUSE Y COORDINATE


*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
