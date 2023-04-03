; ==============================================================================
; INTRO SCREEN
; ==============================================================================

; ------------------------------------------------------------------------------
ISCINIT
; INTRO SCREEN INIT
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            CLR.W   (ISCTIMER)
            JSR     BUTINIT
            RTS

; ------------------------------------------------------------------------------
ISCUPD
; INTRO SCREEN UPDATE
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            JSR     BUTUPD
            BTST.B  #4,(KBDEDGE)
            BEQ     .CONT
            MOVE.W  #1,(STANEXT)
.CONT       ADDQ.W  #1,(ISCTIMER)
            CMP.W   #ISCMAXTM,(ISCTIMER)
            BLT     .DONE
            MOVE.W  #1,(STANEXT)
.DONE       RTS

; ------------------------------------------------------------------------------
ISCPLOT
; INTRO SCREEN PLOT
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L D0-D1/A0,-(A7)
            JSR     BUTPLOT

            CLR.L   D1
            MOVE.B  #81,D0
            TRAP    #15
            
            LEA     .TXT1,A0
            MOVE.B  #10,D0
            JSR     UTLPRINT
                        
            MOVEM.L (A7)+,D0-D1/A0

            RTS
.TXT1       DC.B    'MARCIANOS 68K',0
            DS.W    0

*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
