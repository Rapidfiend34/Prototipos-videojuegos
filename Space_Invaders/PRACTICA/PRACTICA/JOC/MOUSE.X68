; ==============================================================================
; MOUSE
; ==============================================================================

; ------------------------------------------------------------------------------
MOINIT
; MOUSE INIT
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L D0-D1/A0-A1,-(A7)
            MOVE.W  #$0101,D1
            MOVE.B  #60,D0
            TRAP    #15
            MOVEQ   #0,D0
            MOVE.W  D0,(BUTTON_R)
            MOVE.W  D0,(BUTTON_L)
            MOVEM.L (A7)+,D0-D1/A0-A1

            RTS
; ------------------------------------------------------------------------------
MOINTE
; MOUSE UPDATE
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------                
            ; MOUSE READ
            MOVEM.L D0-D1,-(A7)
            MOVEQ   #0,D1
            MOVE.B  #61,D0
            TRAP    #15
            MOVE.W  #0,(BUTTON_L)
            MOVE.W  #0,(BUTTON_R)
            MOVEQ   #1,D1
            BTST    #0,D0
            BEQ     NOT_LEFT
            MOVE.W  D1,(BUTTON_L)
            
NOT_LEFT    
            BTST    #1,D0
            BEQ     NOT_RIGHT
            MOVE.W  D1,(BUTTON_R)
NOT_RIGHT   
            
            MOVEM.L (A7)+,D0-D1
            RTS
MOMOVE      
            MOVEM.L D0-D1/A0-A1,-(A7)
            MOVEQ   #0,D1
            MOVE.B  #61,D0
            TRAP    #15
            
            MOVE.W  D1,(MOUSEX)
            SWAP    D1
            
            MOVE.W  D1,(MOUSEY)
            MOVEM.L (A7)+,D0-D1/A0-A1

            RTS


*~Font name~Fixedsys~
*~Font size~18~
*~Tab type~0~
*~Tab size~4~
