#include <stdio.h>
#include <ctype.h>

void filter(FILE *input, FILE *output)
{
    char c;
    while(!feof(input)) {
        c = fgetc(input);

        if(isalpha(c) || c == '\n') {
            fputc(c, output);
        } else {
            fputc(' ', output);
        }
    }
}

int main(int argc, char const *argv[])
{
    FILE *p_file_read;
    FILE *p_file_write;

    p_file_read  = fopen("thetext.txt", "r");
    p_file_write = fopen("newtext.txt", "w");

    filter(p_file_read, p_file_write);

    fclose(p_file_read);
    fclose(p_file_write);
    return 0;
}