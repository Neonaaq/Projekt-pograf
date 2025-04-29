#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "wypiszgraf.h"

int czypusty(int **macierz, int i, int lwezlow)
{
    for(int j=0;j<lwezlow;j++)
    {
        if(macierz[i][j]==1)    return 0;
    }
    return 1;
}

void wypiszgraf(FILE *plik_we, int **macierz, int lwezlow, struct Flagi *flaga)
{
    int l=0, lczesci = flaga->division, pozycja=0;
    int P[lwezlow];         // pozycje danej liczy w wierszu czwartym
    FILE *plik_wy = fopen(flaga->output_filename, "w");
    char znak;
    int tryb=0;

    rewind(plik_we);
    if(plik_we == NULL || fgetc(plik_we) == EOF) {
        printf("Błąd NULL: Plik jest pusty. Program przerywa działanie.\n");
        fclose(plik_we);
        return;
    }
    rewind(plik_we);
    
    if(flaga->file_mode==BINARY)  tryb=1;

    while(l<3)
    {
        printf("e\n");
        while((znak = fgetc(plik_we)) != '\n' && znak != EOF) 
        {
            printf("d\n");
            if(tryb==0) fputc(znak, plik_wy);
            else        
            {
                if(znak>= '0' && znak <= '9')    fwrite(&znak, sizeof(int), 1, plik_wy);
                else                             fwrite(&znak, sizeof(char), 1, plik_wy);
            }
        }
        if(tryb==0) fputc('\n', plik_wy);
        else        fwrite("\n", sizeof(char), 1, plik_wy);
        l++;
    }

    // czwarty wiersz
    for(int i=0;i<lwezlow;i++)
    {
        P[i] = -1;
    }
    pozycja=0;    // pozycja liczby w 4 wierszu
    for(int i=0;i<lwezlow;i++)
    {
        if(czypusty(macierz, i, lwezlow)==0)
        {
            if(pozycja==0)
            {
                if(tryb==0) fprintf(plik_wy,"%i",i);
                else
                {
                    fwrite(&i, sizeof(int), 1, plik_wy);
                }
            }
            else
            {
                if(tryb==0) fprintf(plik_wy,";%i",i);
                else        
                {
                    fwrite(";", sizeof(char), 1, plik_wy);
                    fwrite(&i, sizeof(int), 1, plik_wy);
                }
            }
            P[i] = pozycja;
            pozycja++;
            for(int j=0; j<lwezlow; j++)
            {
                if(macierz[i][j]==1)
                {
                    if(tryb==0) fprintf(plik_wy,";%i",j);
                    else        
                    {
                        fwrite(";", sizeof(char), 1, plik_wy);
                        fwrite(&i, sizeof(int), 1, plik_wy);
                    }
                    pozycja++;
                    macierz[j][i]=0;
                }
            }
        }
    }
    if(tryb==0) fputc('\n', plik_wy);
    else        fwrite("\n", sizeof(char), 1, plik_wy);

    // piąty wiersz

    if(tryb==0) fprintf(plik_wy,"%i",P[0]);
    else        fwrite(&P[0], sizeof(int), 1, plik_wy);
    for(int i=1; i<lwezlow;i++)
    {
        if(P[i] != -1)
        {
            if(tryb==0) fprintf(plik_wy,";%i",P[i]);
            else        
            {
                fwrite(";", sizeof(char), 1, plik_wy);
                fwrite(&P[i], sizeof(int), 1, plik_wy);
            }
        }
    }

    fclose(plik_we);
}