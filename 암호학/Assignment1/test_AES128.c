/*  ======================================================================== *

                                    주 의 사 항


    1. 계산 중간 흐름은 다음 문서를 보고 비교하는 것을 추천함

        http://csrc.nist.gov/publications/fips/fips197/fips-197.pdf

 *  ======================================================================== */

#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include "AES128.h"

typedef unsigned char BYTE;

BYTE iv[]  = {
    0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
};

BYTE key[] = {
    0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x15, 0x88, 0x09, 0xcf, 0x4f, 0x3c
};

BYTE plain[] = {
    0x6b, 0xc1, 0xbe, 0xe2, 0x2e, 0x40, 0x9f, 0x96, 0xe9, 0x3d, 0x7e, 0x11, 0x73, 0x93, 0x17, 0x2a,
    0xae, 0x2d, 0x8a, 0x57, 0x1e, 0x03, 0xac, 0x9c, 0x9e, 0xb7, 0x6f, 0xac, 0x45, 0xaf, 0x8e, 0x51,
    0x30, 0xc8, 0x1c, 0x46, 0xa3, 0x5c, 0xe4, 0x11, 0xe5, 0xfb, 0xc1, 0x19, 0x1a, 0x0a, 0x52, 0xef,
    0xf6, 0x9f, 0x24, 0x45, 0xdf, 0x4f, 0x9b, 0x17, 0xad, 0x2b, 0x41, 0x7b, 0xe6, 0x6c, 0x37, 0x10
};

BYTE cipher[] = {
    0x76, 0x49, 0xab, 0xac, 0x81, 0x19, 0xb2, 0x46, 0xce, 0xe9, 0x8e, 0x9b, 0x12, 0xe9, 0x19, 0x7d,
    0x50, 0x86, 0xcb, 0x9b, 0x50, 0x72, 0x19, 0xee, 0x95, 0xdb, 0x11, 0x3a, 0x91, 0x76, 0x78, 0xb2,
    0x73, 0xbe, 0xd6, 0xb8, 0xe3, 0xc1, 0x74, 0x3b, 0x71, 0x16, 0xe6, 0x9e, 0x22, 0x22, 0x95, 0x16,
    0x3f, 0xf1, 0xca, 0xa1, 0x68, 0x1f, 0xac, 0x09, 0x12, 0x0e, 0xca, 0x30, 0x75, 0x86, 0xe1, 0xa7
};

void printResult(char *keyword, uint8_t *data) {
    int i = 0;
    printf(" - %s : ", keyword);
    for(i = 0; i < 64; i++) {
        if(i % 16 == 0) printf("\n");
        printf("%02x ", data[i]);
    }
    printf("\n");
}

int main() {
    int i, j;
    BYTE result[64], tmpBlock[16];

    // 암호화 테스트
    memcpy(result, plain, sizeof(BYTE)*64);

    printResult("Plain Text", plain);

    for(i = 0; i < 4; i++) {
        for(j = 0; j < 16; j++)
            if(i == 0)
                result[j] = iv[j] ^ result[j];
            else
                result[i * 16 + j] = result[(i - 1) * 16 + j] ^ plain[i * 16 + j];
        AES128(result + i * 16, tmpBlock, key, ENC);
    }

    printResult("Encrypted Plain Text", result);

    printf("==================================\n");
    printf("AES Encryption: %s\n", 0 == strncmp((char*) cipher, (char*) result, 64) ? "SUCCESS!" : "FAILURE!");
    printf("==================================\n");

    // 복호화 테스트
    memcpy(result, cipher, sizeof(BYTE)*64);

    printResult("Cipher Text", cipher);

    for (i = 0; i < 4; i++) {
        AES128(result + i * 16, tmpBlock, key, DEC);
        for (j = 0; j < 16; j++)
            if(i == 0)
                result[j] = iv[j] ^ tmpBlock[j];
            else
                result[i * 16 + j] = cipher[(i - 1) * 16 + j] ^ tmpBlock[j];
    }

    printResult("Decrypted Cipher Text", result);

    printf("==================================\n");
    printf("AES Decryption: %s\n", 0 == strncmp((char*) result, (char*) plain, 64) ? "SUCCESS!" : "FAILURE!");
    printf("==================================\n");

    return 0;
}
