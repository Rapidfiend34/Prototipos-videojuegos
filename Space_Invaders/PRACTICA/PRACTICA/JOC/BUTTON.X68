; ==============================================================================
; BUTTON MANAGEMENT
; ==============================================================================
            
; ------------------------------------------------------------------------------
BUTINIT
; INITIALIZE SHIP.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVE.W  #0,(TOUCHED)     
            MOVE.L  #0,(BUTCOL1)
            MOVE.L  #0,(BUTCOL2)
            MOVE.L  #0,(BUTCOL3)       
            RTS
            
; ------------------------------------------------------------------------------
BUTUPD
; UPDATES THE BUTTONS.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            
            ; CHEKS IF THE MOUSE IS ON A BUTTON
            MOVEM.L D0-D1,-(A7)
            MOVE.W  (MOUSEX),D0
            MOVE.W  #SCRWIDTH/2,D1
            SUB.W   #BUTWIDTH/2,D1
            CMP.W   D1,D0
            BLT     .RESTORE
            ADD.W   #BUTWIDTH,D1
            CMP.W   D1,D0
            BGT     .RESTORE
            MOVE.W  (MOUSEY),D0

            ; CHKBUT1
            MOVE.W  #BUTPOSY1,D1
            CMP.W   D1,D0
            BLT     .CHKBUT2
            ADD.W   #BUTHEIGH,D1
            CMP.W   D1,D0
            BGT     .CHKBUT2
            MOVE.L  #$00000FFF,(BUTCOL1)
            MOVE.L  #0,(BUTCOL3)
            MOVE.L  #0,(BUTCOL2)

            MOVE.W  (BUTTON_L),D1
            CMP.W   #1,D1
            BNE     .EXIT
            MOVE.W  #2,(STANEXT)
            MOVE.W   #0,(BUTTON_L)
            BRA     .EXIT

.CHKBUT2    
            MOVE.W  #BUTPOSY2,D1
            CMP.W   D1,D0
            BLT     .CHKBUT3
            ADD.W   #BUTHEIGH,D1
            CMP.W   D1,D0
            BGT     .CHKBUT3
            MOVE.L  #$00000FFF,(BUTCOL2)
            MOVE.L  #0,(BUTCOL3)
            MOVE.L  #0,(BUTCOL1)
            MOVE.W  (BUTTON_L),D1
            CMP.W   #1,D1
            BNE     .EXIT
            MOVE.W  #1,(STANEXT)
            MOVE.W   #0,(BUTTON_L)
            BRA     .EXIT
.CHKBUT3    
            MOVE.W  #BUTPOSY3,D1
            CMP.W   D1,D0
            BLT     .RESTORE
            ADD.W   #BUTHEIGH,D1
            CMP.W   D1,D0
            BGT     .RESTORE
            MOVE.L  #$00000FFF,(BUTCOL3)
            MOVE.L  #0,(BUTCOL1)
            MOVE.L  #0,(BUTCOL2)
            MOVE.W  (BUTTON_L),D1
            CMP.W   #1,D1
            BNE     .EXIT
            MOVE.B  #33,D0
            MOVE.L  #0,D1
            TRAP    #15
            TRAP    #0
            SIMHALT
            BRA     .EXIT
.RESTORE
            MOVE.L  #0,(BUTCOL1)
            MOVE.L  #0,(BUTCOL2)
            MOVE.L  #0,(BUTCOL3)
 
.EXIT       MOVEM.L  (A7)+,D0-D1

            RTS
            
; ------------------------------------------------------------------------------
BUTPLOT
; PLOT THE SHIP.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.L D0-D4/D1-D2,-(A7)

            
; BUTCOL1__________________________

            ; SET CONTOUR COLOR
            MOVE.B  #80,D0
            MOVE.L  #MOTPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  (BUTCOL1),D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  #SCRWIDTH/2,D1
            SUB.W   #BUTWIDTH/2,D1
            MOVE.W  #BUTPOSY1,D2
            MOVE.W  #SCRWIDTH/2,D3
            ADD.W   #BUTWIDTH/2,D3
            MOVE.W  D2,D4
            ADD.W   #BUTHEIGH,D4
                      
            ; DRAW RECTANGLE
            MOVE.B  #87,D0
            TRAP    #15
            
            LEA     .PLAY,A0
            MOVE.B  #15,D0
            JSR     UTLPRINT

   
; BUTCOL2__________________________

            ; SET CONTOUR COLOR
            MOVE.B  #80,D0
            MOVE.L  #MOTPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  (BUTCOL2),D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  #SCRWIDTH/2,D1
            SUB.W   #BUTWIDTH/2,D1
            MOVE.W  #BUTPOSY2,D2
            MOVE.W  #SCRWIDTH/2,D3
            ADD.W   #BUTWIDTH/2,D3
            MOVE.W  D2,D4
            ADD.W   #BUTHEIGH,D4
                      
            ; DRAW RECTANGLE
            MOVE.B  #87,D0
            TRAP    #15
            
            LEA     .INST,A0
            MOVE.B  #20,D0
            JSR     UTLPRINT


; BUTCOL3__________________________

            ; SET CONTOUR COLOR
            MOVE.B  #80,D0
            MOVE.L  #MOTPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  (BUTCOL3),D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  #SCRWIDTH/2,D1
            SUB.W   #BUTWIDTH/2,D1
            MOVE.W  #BUTPOSY3,D2
            MOVE.W  #SCRWIDTH/2,D3
            ADD.W   #BUTWIDTH/2,D3
            MOVE.W  D2,D4
            ADD.W   #BUTHEIGH,D4
                       
            ; DRAW RECTANGLE
            MOVE.B  #87,D0
            TRAP    #15
            
            LEA     .LEAVE,A0
            MOVE.B  #25,D0
            JSR     UTLPRINT




            MOVEM.L (A7)+,D1-D2/D0-D4

            RTS
            
            ; WRITE TEXT ON THE BUTTONS
            
.PLAY       DC.B    'START',0
.INST       DC.B    'INSTRUCTIONS',0
.LEAVE      DC.B    'EXIT',0
            DS.W    0


*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
