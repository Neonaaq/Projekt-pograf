#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "odczytajgraf.h"
#include "args.h"
#include "podziel_graf.h"

int main(int argc, char *argv[])
{
    Komorka *p = malloc(sizeof(Komorka));
    int **macierz, lwezlow;
    FILE *plik = fopen(argv[1],"r");
    struct Flagi flaga;

    lwezlow = odczytaj_graf(&p, &macierz, plik);
    if(lwezlow == -1){
        return 0;
    }

    if(parse_args(argc, argv, &flaga, lwezlow) != 0) {
        return 1;
    }

    podziel_graf(p, macierz, lwezlow, &flaga);

    //Test czy flagi sa poprawnie wczytywane
    
    //printf("Division = %d\n", flaga.division);
    //printf("Hem = %d\n", flaga.hem);
    //printf("Tryb pliku: %s\n", flaga.file_mode == TEXT ? "tekstowy" : "binarny");
    //printf("Plik wyjsciowy: %s\n", flaga.output_filename);

    // Test, czy macierz dobrze ma zapisane połączenia np dla 0:

     //for(int i=0; i<lwezlow; i++) {
     //    if(macierz[0][i]==1)
     //        printf("%d\n", i);
     //}

    //  Wizualizacja grafu:
    
     //int arow = 0, acol = 0;
     //for(int i=0; i<lwezlow; i++) {
     //    while(p[i].row!=arow) {
     //        arow++;
     //        acol=0;
     //        printf("\n");
     //    }
     //    while(p[i].column!=acol) {
     //        acol++;
     //        printf("   ");
     //    }
     //    acol++;
     //    if(i>=0 && i <10) printf(" %d ", i);
     //    if(i>=10 && i <100) printf(" %d", i);
     //    if(i>=100) printf("%d", i);
     //}
    //printf("\n");


    // Sprawdzenie, czy dobrze zostaly podzielone

    //FILE *out = fopen(flaga.output_filename, flaga.file_mode == TEXT ? "w" : "wb");
    //for (int i = 0; i < lwezlow; i++) {
    //    fprintf(out, "%d %d\n", i, p[i].part + 1);
    //}
    //fclose(out);

    free(p);
    for(int i=0;i<lwezlow;i++) {
        free(macierz[i]);
    }
    free(macierz);
    return 0;
}
