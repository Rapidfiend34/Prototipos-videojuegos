; ==============================================================================
; INSTRUCTIONS SCREEN
; ==============================================================================

; ------------------------------------------------------------------------------
INSINIT
; INSTRUCTIONS SCREEN INIT
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            RTS

; ------------------------------------------------------------------------------
INSUPD
; INSTRUCTIONS SCREEN UPDATE
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            BTST.B  #4,(KBDEDGE)
            BEQ     .CONT
            MOVE.W  #2,(STANEXT)
.CONT       ADDQ.W  #1,(ISCTIMER)
            CMP.W   #INSMAXTM,(ISCTIMER)
            BLT     .DONE
            MOVE.W  #2,(STANEXT)
.DONE       RTS


; ------------------------------------------------------------------------------
INSPLOT
; INSTRUCTIONS SCREEN PLOT
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.L D0-D1/A0-A1,-(A7)
            MOVE.B  #11,D0
            LEA     .TXTLST,A1
.LOOP       MOVE.L  (A1)+,D1
            BEQ     .PARP
            MOVE.L  D1,A0
            JSR     UTLPRINT
            BRA     .LOOP
            
.PARP       BTST.B  #4,(SCRINTCY)
            BEQ     .DONE
            
            LEA     .TXT12,A0
            MOVE.B  #32,D0
            JSR     UTLPRINT
            
.DONE       MOVEM.L (A7)+,D0-D1/A0-A1
            RTS
            
.TXT1       DC.B    'WELCOME TO THE MARCIANOS 68K!',0
.TXT2       DC.B    'YOUR GOAL IN THIS GAME IS TO KILL THE MOST ALIENS THAT YOU CAN',0
.TXT3       DC.B    'TO THIS GOAL, MOVE YOUR SHIP USING THE KEYBOARD',0
.TXT4       DC.B    'HOW TO DO IT? YOU MAY ASK.',0
.TXT5       DC.B    'IT IS EASY: JUST USE THE UP KEY TO MOVE THE SHIP...',0
.TXT6       DC.B    'UP!',0
.TXT7       DC.B    'AND USE THE DONW KEY TO MOVE THE SHIP DOWN.',0
.TXT8       DC.B    'WHAT ABOUT LEFT AND RIGHT?',0
.TXT9       DC.B    'YEAH. THESE ARE USED TO HORIZONTAL DIRECTIONS',0
.TXT10      DC.B    'AND HOW CAN YOU KILL THEM?',0
.TXT11      DC.B    'EASY JUST JUST WITH THE KEY "X" ON YOUR KEYBOARD',0
.TXT12      DC.B    'PRESS "X" TO START',0
            DS.W    0
.TXTLST     DC.L    .TXT1,.TXT2,.TXT3,.TXT4,.TXT5,.TXT6,.TXT7,.TXT8,.TXT9,.TXT10,.TXT11,0



*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
