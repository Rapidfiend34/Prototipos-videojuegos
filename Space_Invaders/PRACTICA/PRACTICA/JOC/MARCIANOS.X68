; ==============================================================================
; MARCIANO MANAGEMENT (AGENT)
; SUMMAR    (W)      (A0)
; MARHEIGH  (W)     2(A0)
; MARSPEDX  (W)     4(A0)
; ==============================================================================
            
; ------------------------------------------------------------------------------
MARINIT
; INITIALIZE MARCIANO.
; INPUT    : NONE
; OUTPUT   : NONE
; MOIFIES  : NONE
; ------------------------------------------------------------------------------
            MOVEM.L  D0/A0,-(A7)
            MOVE.W  #MARHEIGH,D0
            NEG.W   D0
            MOVE.W  SUMMAR,(A0)
            MOVE.W  D0,2(A0)
            MOVE.W  MARSPEDX,4(A0)
            MOVEM.L (A7)+,D0/A0
            RTS
            
; ------------------------------------------------------------------------------
MARUPD
; UPDATES THE MARCIANO.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.W D0-D2/A0-A1,-(A7)
            ; UPDATE MARCIANOS COORDINATES
            CMP.W   #4,(MAXSHTMA)
            BGT     .NOSHOT
            LEA     RANDVEC,A1
            MOVE.W  (RDMCOUNT),D1
            MOVE.B  (A1,D1.W),D0
            MOVE.B  (SCRINTCY),D2
            AND.B   #$64,D2
            BEQ     .CHKRN
            AND.B   #$35,D2
            BNE     .NOTGUIDE
.CHKRN      CMP.B   #253,D0
            BNE     .NOTGUIDE
            MOVE.W  (NUMMAR),D1
            CMP.W   #7,D1
            BNE     .NOTGUIDE
            JSR     AGLKILL
            LEA     GUIDEINT,A1
            LEA     GUIDEIPD,A2
            LEA     GUIDEPLT,A3
            MOVE.W  #4,D0
            LEA.L   (A0),A4
            JSR     AGLADD

.NOTGUIDE   LEA     RANDVEC,A1
            MOVE.W  (RDMCOUNT),D1
            MOVE.B  (A1,D1.W),D0
            CMP.B   #10,D0
            BLT     .NOSHOT
            CMP.B   #11,D0
            BGT     .NOSHOT
            LEA     SHTMAINT,A1
            LEA     SHTMAUPD,A2
            LEA     SHTMAPLT,A3
            MOVE.W  #3,D0
            LEA.L   (A0),A4
            JSR     AGLADD
            ADDQ.W  #1,(MAXSHTMA)
.NOSHOT     ADDQ.W  #1,(RDMCOUNT)
            LEA.L   (A0),A1
            MOVE.W  (RDMCOUNT),D0
            CMP.W   #248,D0
            BLT     .NEXTRDM
            CLR.W   (RDMCOUNT)
.NEXTRDM    
            CMP.W  #SCRHEIGH-MARHEIGH/2,2(A1)
            BLT     .MOVEMENTY
            JSR     AGLKILL
            SUBQ.W  #1,(MAXMAR)
            CMP.W   #0,(SCOOUT)
            BEQ     .MOVEMENTY
            SUB.W  #POINTS,(SCOOUT)
.MOVEMENTY  MOVE.W  (MARTIMER),D0
            BTST.L  #$1,D0
            BGT     .NOMOVEY         
            MOVE.W  2(A1),D1
            ADD.W   #MARSPEDY,D1
            

            ; STORE POSITION
            MOVE.W  D1,2(A1)
            
.NOMOVEY    MOVE.W  (A1),D0
            MOVE.W  #SCRWIDTH,D1      
            ADD.W   #MARWIDTH/2,D0
            CMP.W   D1,D0
            BLT     .CHKLEFT
            NEG.W   4(A1)

            BRA     .CHKCOLL
.CHKLEFT    MOVE.W  #0,D1
            MOVE.W  (A1),D0
            SUB.W   #MARWIDTH/2,D0
            CMP.W   D1,D0
            BGT     .DONE
            NEG.W   4(A1)
.CHKCOLL         
            MOVE.W  #2,D0
            JSR     DMMFRSTO
.LOOP       
            CMP.L   #0,A0
            BEQ     .DONE 
            ADD.L   #8,A0
            MOVE.W  (A0),D2
            MOVE.W  (A1),D1
            CMP.W   D2,D1
            BEQ     .NEXT
            MOVE.W  2(A0),D2
            MOVE.W  2(A1),D1
            ADD.W   #MARHEIGH/2,D2
            CMP.W   D2,D1
            BGT     .NEXT
            SUB.W   #MARHEIGH,D2
            CMP.W   D2,D1
            BLT     .NEXT
            MOVE.W  4(A1),D2
            MOVE.W   D2,4(A0)  
            BRA     .NEXT 


.NEXT       JSR     DMMNEXTO
            BRA     .LOOP            
.DONE       
            MOVE.W  (A1),D0
            ADD.W   4(A1),D0
.END        MOVE.W  D0,(A1)

;.NOMOVEY
       MOVEM.W (A7)+,D0-D2/A0-A1

            RTS
            
; ------------------------------------------------------------------------------
MARPLOT
; PLOT THE MARCIANO.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.L D0-D4,-(A7)

; CIRCLE__________________________________

            ; SET PEN COLOR
            MOVE.B  #80,D0
            MOVE.L  #MCIRPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #MCIRFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (A0),D1
            SUB.W   #MARWIDTH/4,D1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/4,D2
            MOVE.W  (A0),D3
            ADD.W   #MARWIDTH/4,D3
            MOVE.W  2(A0),D4
            ADD.W   #MARHEIGH/4,D4
            
            ; DRAW CIRCLE
            
            MOVE.B  #88,D0
            TRAP    #15
            

; LEFT RECTANGLE__________________________
            
            ; SET PEN COLOR
            MOVE.B  #80,D0
            MOVE.L  #MRECPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #MRECFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (A0),D1
            SUB.W   #MARWIDTH/2,D1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/2,D2
            MOVE.W  (A0),D3
            SUB.W   #MARWIDTH/3,D3
            MOVE.W  2(A0),D4
            ADD.W   #MARHEIGH/2,D4
            
            ; DRAW LEFT RECTANGLE
            
            MOVE.B  #87,D0
            TRAP    #15
            
; RIGHT RECTANGLE_________________________

            ; SET PEN COLOR
            MOVE.B  #80,D0
            MOVE.L  #MRECPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #MRECFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (A0),D1
            ADD.W   #MARWIDTH/3,D1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/2,D2
            MOVE.W  (A0),D3
            ADD.W   #MARWIDTH/2,D3
            MOVE.W  2(A0),D4
            ADD.W   #MARHEIGH/2,D4
            
            ; DRAW RIGHT RECTANGLE
            
            MOVE.B  #87,D0
            TRAP    #15

            
            MOVEM.L (A7)+,D0-D4

            RTS

; ------------------------------------------------------------------------------
RANDVEC
; PROVIDES RANDOM NUMBERS.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            DC.B    162, 238, 154, 12 , 245, 36 , 32 , 103
            DC.B    42 , 163, 81 , 38 , 224, 172, 19 , 69
            DC.B    117, 81 , 202, 253, 51 , 235, 108, 154
            DC.B    96 , 204, 231, 184, 154, 235, 34 , 203
            DC.B    29 , 118, 181, 149, 176, 137, 62 , 108
            DC.B    57 , 21 , 72 , 201, 110, 202, 159, 41
            DC.B    67 , 231, 226, 124, 55 , 37 , 84 , 62
            DC.B    233, 148, 206, 233, 228, 82 , 243, 78
            DC.B    55 , 109, 154, 132, 162, 40 , 30 , 39
            DC.B    25 , 226, 198, 119, 128, 212, 232, 225
            DC.B    10 , 90 , 50 , 103, 51 , 194, 222, 40
            DC.B    89 , 128, 220, 14 , 87 , 252, 243, 252
            DC.B    138, 171, 188, 247, 7  , 77 , 29 , 234
            DC.B    239, 213, 119, 110, 30 , 187, 131, 23
            DC.B    214, 224, 42 , 143, 189, 40 , 212, 130
            DC.B    19 , 79 , 102, 236, 10 , 100, 122, 188
            DC.B    86 , 82 , 154, 22 , 155, 144, 7  , 199
            DC.B    6  , 138, 164, 247, 1  , 218, 166, 82
            DC.B    242, 231, 119, 40 , 177, 118, 220, 102
            DC.B    164, 66 , 227, 129, 177, 103, 165, 108
            DC.B    191, 206, 250, 22 , 191, 56 , 30 , 161
            DC.B    209, 139, 80 , 12 , 95 , 73 , 168, 56
            DC.B    222, 76 , 20 , 162, 64 , 220, 93 , 12
            DC.B    182, 155, 10 , 56 , 93 , 171, 89 , 6
            DC.B    226, 27 , 221, 117, 21 , 130, 101, 18
            DC.B    138, 38 , 81 , 48 , 159, 71 , 57 , 21
            DC.B    30 , 138, 187, 113, 198, 110, 185, 152
            DC.B    159, 71 , 229, 52 , 148, 39 , 77 , 6
            DC.B    125, 6  , 214, 186, 48 , 15 , 212, 22
            DC.B    236, 127, 126, 3  , 207, 45 , 193, 2
            DC.B    148, 91 , 35 , 242, 139, 205, 248, 237
            DC.B    116, 51 , 236, 185, 15 , 213, 221, 6


*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
