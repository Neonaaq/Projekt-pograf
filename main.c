#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "odczytajgraf.h"

int main(int argc, char* argv[])
{
    Komorka *p = malloc(sizeof(Komorka));
    int **macierz, lwezlow;
    FILE *plik = fopen("graf.csrrg","r");

    lwezlow = odczytaj_graf(&p, &macierz, plik);
    if(lwezlow == -1){
        return 0;
    }

    // Test, czy macierz dobrze ma zapisane połączenia np dla 0:

    // for(int i=0; i<lwezlow; i++) {
    //     if(macierz[0][i]==1)
    //         printf("%d\n", i);
    // }

    //  Wizualizacja grafu:
    
    int arow = 0, acol = 0;
    for(int i=0; i<lwezlow; i++) {
        while(p[i].row!=arow) {
            arow++;
            acol=0;
            printf("\n");
        }
        while(p[i].column!=acol) {
            acol++;
            printf("   ");
        }
        acol++;
        if(i>=0 && i <10) printf(" %d ", i);
        if(i>=10 && i <100) printf(" %d", i);
        if(i>=100) printf("%d", i);
    }

    free(p);
    for(int i=0;i<lwezlow;i++) {
        free(macierz[i]);
    }
    free(macierz);
    return 0;
}