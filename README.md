# Cryptographer's Right Hand: decryptor using letter frequencies
A ciphertext decryptor that uses letter frequencies to guess which letter corresponds to which. Great for partially decrypting substitution ciphering.

This project first calculates the frequency of each letter in the given encrypted file. This way, it gets the most frequent 4 letters in the encrypted file. Then, it replaces these letters with the 4 most frequent letters in the English texts, which are 'E', 'T', 'A', and 'O'. Since, say, the most frequent letter in the encrypted file does not have to be the encrypted letter of 'E', this class tries to decrypt using permutations of these 4 letters. Hence, it assumes 'E', 'T', 'A', and 'O' are the most frequent 4 letters - without having to be in this order so it creates 4! = 24 possible files - in the non-encrypted original plaintext. Then, the user can find out which of the 24 partially decrypted files actually corresponds to the non-encrypted original plaintext.

Here is a screenshot of a text, its encrypted version using Caeser Cipher with 7 letter shift, and my algorithm's successful decryption.
<img width="1440" alt="Screen Shot 2022-01-09 at 14 40 33" src="https://user-images.githubusercontent.com/96665962/148680828-0519472f-7fb9-48e7-8599-0926c80aa532.png">
