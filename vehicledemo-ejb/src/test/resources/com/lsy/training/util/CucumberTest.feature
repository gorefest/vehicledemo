Feature: Test Addition
	In order to test the add function, we add some numbers
Scenario Outline: perform add
	Given a number <n1> entered into the calculator
	  And a number <n2> entered into the calculator
	 When I perform add function
	 Then result will be <result>
Examples:
| n1 | n2 | result |
| 5  | 6  | 11     |
| 7  | 8  | 15     |
| 12 | 24 | 36     |	 