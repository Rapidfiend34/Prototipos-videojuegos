; ==============================================================================
; GAME MANAGEMENT
; ==============================================================================

; ------------------------------------------------------------------------------
GAMINIT
; INITIALIZES GAME.
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            JSR     DMMINIT
            JSR     SHIPINIT
            BRA     SCOINIT

; ------------------------------------------------------------------------------
GAMUPD
; UPDATES THE GAME
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L D0-D7/A1-A3,-(A7)
            ; LIMITATES THE SHOOTS PER SECOND
            CMP.W  #SHOTWAIT,(SHOTIMER)
            BLT     .GOUPD
            ; CHECK SHOOTS KEYBOARD
            BTST.B  #4,(KBDEDGE)
            BEQ     .GOUPD
            MOVE.B  #2,D7
            TRAP    #2
            ; INICIALIZE PARAMETERS FOR AGENTS SHOOT
            LEA     SHOTINIT,A1
            LEA     SHOTUPD,A2
            LEA     SHOTPLOT,A3
            MOVE.W  #1,D0
            CLR.W   (SHOTIMER)
            JSR     AGLADD
.GOUPD    
            CMP.W   #31,(MAXMAR)
            BGT     .CONTINU
            MOVE.W  (NUMMAR),D6
            ; CHECK ROW CREATION TIMER
            MOVE.W  (ROWTIMER),D0
            CMP.W   #$A0,(ROWTIMER)
            BLT     .CONTINU
            
.GENLOOP    CLR.W   (ROWTIMER)
            LEA     MARINIT,A1
            LEA     MARUPD,A2
            LEA     MARPLOT,A3
            MOVE.W  #2,D0
            MOVE.W  #1,D7
            JSR     AGLADD
            ADD.W   #MARWIDTH,(SUMMAR)
            ADD.W   #MARWIDTH/2,(SUMMAR)
            ADDQ.W  #1,(MAXMAR)
            DBRA    D6,.GENLOOP
            MOVE.W  #$4F,(SUMMAR)


.CONTINU    JSR     AGLUPD
            JSR     SHIPUPD
            CMP.W  #0,(LIFES)
            BNE    .DONE
            MOVE.W #4,(STANEXT)
            MOVE.W #3,(LIFES)
            CLR.W  (LENGLIFE)
            CLR.W  (MAXMAR)
            CLR.W  (MAXSHTMA)

.DONE       MOVEM.L (A7)+,D0-D7/A1-A3
            RTS

; ------------------------------------------------------------------------------
GAMPLOT
; PLOTS THE GAME
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            JSR     AGLPLOT
            JSR     SCOPLOT
            BRA     SHIPPLOT


*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
