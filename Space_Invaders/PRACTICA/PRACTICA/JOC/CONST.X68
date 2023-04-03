; ==============================================================================
; GAME CONSTANTS
; ==============================================================================

; --- SHOOT RELATED CONTANTS ---------------------------------------------------

SHOTPCOL    EQU     $AA000000               ; SHOOT CONTOUR COLOR
SHOTFCOL    EQU     $FF000000               ; SHOOT FILL COLOR
SHOTRAD     EQU     8                       ; SHOOT BALL'S RADIUS
SHOTSPED    EQU     15                      ; SHOOT SPEED
SHOTWAIT    EQU     $10                     ; HELPS TO MODERATE SHOOT MOVEMENT

; --- SHIP RELATED CONSTANTS ---------------------------------------------------

SHTMAWIT    EQU     5                       ; MARCIANOS SHOOT RECTANGLE'S WIDTH
SHTMAHEI    EQU     10                      ; MARCIANOS SHOOT RECTANGLE'S WIDTH

; --- BUTTON RELATED CONSTANTS -------------------------------------------------
BUTPOSY1    EQU     205                     ; POSITION BUTTON 1 (START)
BUTPOSY2    EQU     280                     ; POSITION BUTTON 2 (INSTRUCCIONS)
BUTPOSY3    EQU     355                     ; POSITION BUTTON 3 (EXIT)
BUTWIDTH    EQU     125                     ; WIDTH OF ALL THE BUTTONS
BUTHEIGH    EQU     50                      ; HEIGHT OF ALL THE BUTTONS

; --- SHIP RELATED CONSTANTS ---------------------------------------------------

SHIPWIDT    EQU     16                      ; SHIP WIDTH
SHIPHEIG    EQU     64                      ; SHIP HEIGHT
SHIPSPED    EQU     10                      ; MAGNITUDE OF THE Y DISPLACEMENT
SHIPPCOL    EQU     $0000FF00               ; SHIP CONTOUR COLOR
SHIPFCOL    EQU     $0000AA00               ; SHIP FILL COLOR

NUCPCOL     EQU     $00FF0000               ; CENTRAL SHIP'S PART CONTOUR COLOR
NUCFCOL     EQU     $00FF0000               ; CENTRAL SHIP'S PART FILL COLOR
MOTPCOL     EQU     $00FFFF00               ; SHIP'S MOTORS CONTOUR COLOR
MOTFCOL     EQU     $00FFFF00               ; SHIP'S MOTORS FILL COLOR

; --- MARCIANO RELATED CONSTANTS -----------------------------------------------

MARWIDTH    EQU     46                      ; MARCIANO'S WIDTH
MARHEIGH    EQU     46                      ; MARCIANO'S HEIGHT
MARSPEDY    EQU     1                       ; MARCIANO'S SPEED

MCIRPCOL    EQU     $001111FF               ; CENTRAL MARCIANO'S CONTOUR COLOR
MCIRFCOL    EQU     $000000AA               ; CENTRAL MARCIANO'S FILL COLOR
MRECPCOL    EQU     $00800080               ; MARCIANO'S MOTORS CONTOUR COLOR
MRECFCOL    EQU     $00800080               ; MARCIANO'S MOTORS FILL COLOR

; --- MARGUIDE RELATED CONSTANTS -----------------------------------------------

GUIWIDTH    EQU     48                      ; MARGUIDE'S WIDTH
GUIHEIGH    EQU     48                      ; MARGUIDE'S HEIGHT
GUISPEED    EQU     2                       ; MARGUIDE'S SPEED

GCIRPCOL    EQU     $00000080               ; CENTRAL MARGUIDE'S CONTOUR COLOR
GCIRFCOL    EQU     $00000080               ; CENTRAL MARGUIDE'S FILL COLOR
GRECPCOL    EQU     $0080FFFF               ; MARGUIDE'S MOTORS CONTOUR COLOR
GRECFCOL    EQU     $0080FFFF               ; MARGUIDE'S MOTORS FILL COLOR

; --- SCOREBOARD RELATED CONSTANTS ---------------------------------------------

SCOFCOL     EQU     $00000000               ; TEXT BACKGROUND COLOR
SCOLINE1    EQU     $4201                   ; FIRST TEXT LINE COORDINATES
SCOLINE2    EQU     $4202                   ; SECOND TEXT LINE COORDINATES
SCOLINE3    EQU     $0101                   ; THIRD  TEXT LINE COORDINATES
POINTS      EQU     50                      ; POINTS FOR EACH MARCIANO KILLED

; --- STATE RELATED CONSTANTS --------------------------------------------------

STANONE     EQU     -1                      ; NON-EXISTENT STATE NUMBER
ISCMAXTM    EQU     500                     ; INTRO SCREEN WATING CYCLES
INSMAXTM    EQU     1000

*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
