	AREA	Countdown, CODE, READONLY
	IMPORT	main
	EXPORT	start
	
start
	MOV R0, #1			; validWord = true
	LDR R1, =cdWord			; adrA = Load start address of word
	LDR R2, =cdLetters		; adrB =Load start address of letters
	LDR R6, =0x23			; loading in '#' 
	MOV R7, R1			; adrSize = adrA
	LDR R8, =0			; countSize = 0;
	 
whileSize 
	LDRB R9, [R7]			; elementSize = memory.Byte [adrSize]
	CMP  R9, #0			; while(	elementSize != 0) 
	BEQ endWhSize			; { 
	ADD R8,R8, #1			; countSize=++ 
	ADD R7,R7, #1			; adrSize++ 
	CMP R9, #0x61			; if( elementSize < 'a' 
	BLO notLetter			; && 
	CMP R9, #0x7A			; elementSize > 'z') 
	BLS whileSize			; { 
notLetter	
	MOV R0, #0			; isValidWord = false   }
	B whileSize			; }

endWhSize
	CMP R8, #10			; if( countSize >= 10) 
	BLO elemAWh			; { 
	MOV R0, #0			; isValidWord = false   
	
elemAWh					; }
	LDRB R3, [R1]			; elementA = memory.Byte [adrA]
	CMP R0, #0			; while (isValidWord 
	BEQ endElemAWh			; &&
	CMP R3, #0			; elementA != 0) 
	BEQ endElemAWh			; { 
	
	MOV R5, R2			; tempAdr = adrB; 
	LDRB R4, [R5]			; elementB = memory.Byte [tempAdr]
elemBWh
	CMP R4, #0			; while( elementB != 0 ) 
	BEQ endElemBWh			; { 
	CMP R3, R4			; if(elementB == elementA) 
	BNE notEqual			; {
	STRB R6, [R5]			; memory.Byte [tempAdr} = '#'
	ADD R1, R1, #1			; adrA++ 
	B   endElemBWh			; } 
	
notEqual			   	 ; else {
	ADD R5, R5, #1			; tempAdr++
	LDRB R4, [R5]			; elementB = memory.Byte [tempAdr]
	B elemBWh			; }
	
endElemBWh	
	CMP R4,#0			; if (elementB == 0)  
	BNE elemAWh			; { 
	MOV R0, #0			; isValidWord = false
	B elemAWh			; }
endElemAWh				; }	
stop	B	stop

	AREA	TestData, DATA, READWRITE

cdWord
	DCB	"beets",0

cdLetters
	DCB	"deatebzsb",0
	
	END	
