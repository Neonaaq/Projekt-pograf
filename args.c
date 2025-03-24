#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <getopt.h>
#include "args.h"

int parse_args(int argc, char *argv[], struct Flagi *flaga, int lwezlow) {
	
	int opt;

	static struct option long_options[] = {
	{"division", required_argument, 0, 'd'},
	{"hem", required_argument, 0, 'h'},
	{"file", required_argument, 0, 'f'},
	{"output", required_argument, 0, 'o'},
	{0, 0, 0, 0}
	};

	//Ustawienie domyslnych wartosci
	flaga->division = 2;
	flaga->hem = 10;
	flaga->file_mode = TEXT;
	flaga->output_filename = "dane_wyjsciowe.txt";

	//Czytanie flag
	while((opt = getopt_long(argc, argv, "d:h:f:o:", long_options, NULL)) != -1) {
		switch(opt) {
			case 'd':
				flaga->division = atoi(optarg);
				if (flaga->division < 2 || flaga->division > lwezlow) {
					printf("Blad DVS: Liczba czesci musi zawierac sie w przedziale <1, %d>. Zostanie ustawiona domyslna wartosc = 2.\n", lwezlow);
					flaga->division = 2;
				}
				break;
			case 'h':
				flaga->hem = atoi(optarg);
				if (flaga->hem <= 0 || flaga->hem > 30) {
					printf("Blad HEM: Margines procentowy musi zawierac sie w przedziale (0, 30>. Zostanie ustawiona domyslna wartosc = 10.\n");
					flaga->hem = 10;
				}
				break;
			case 'f':
				if (strcmp(optarg, "txt") == 0) {
					flaga->file_mode = TEXT;
				}
				else if (strcmp(optarg, "bin") == 0) {
					flaga->file_mode = BINARY;
				}
				else
					fprintf(stderr, "Blad MODE: Nieprawidlowy tryb pliku %s (dozwolone: txt, bin). Zostanie ustawiony domyslny tryb: txt.\n", optarg);
				break;
			case 'o':
				flaga->output_filename = optarg;
				break;
			case '?':
				printf("Blad: Nieznana opcja.\n");
				return 1;
			default:
				printf("Blad: Nieoczekiwany blad.\n");
				return 1;
		}
	}
	return 0;
}