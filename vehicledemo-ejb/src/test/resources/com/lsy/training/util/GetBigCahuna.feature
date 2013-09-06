Feature: Find biggest engine car
	Bla bla bla
Scenario Outline: Locate the car carrying the biggest engine
	Given a list of cars with <engine1>,<engine2>,<engine3> hp
	Given a list of vendors <v1>,<v2>,<v3> associated with the matching cars
	When the method is called find the car with the biggst engine
	Then the found vendor should be <v2>

Examples: 

|engine1 	| engine2 	| engine3	| v1 | v2 | v3 |
|100		|200		|150		|  1   |   3  |  4   |