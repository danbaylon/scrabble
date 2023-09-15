export class Input {
    char1: string = '';
    char2: string = '';
    char3: string = '';
    char4: string = '';
    char5: string = '';
    char6: string = '';
    char7: string = '';
    char8: string = '';
    char9: string = '';
    char10: string = '';
    score: number = 0;

    clear() {
        this.char1 = '';
        this.char2 = '';
        this.char3 = '';
        this.char4 = '';
        this.char5 = '';
        this.char6 = '';
        this.char7 = '';
        this.char8 = '';
        this.char9 = '';
        this.char10 = '';
    }

    generateWord() : string {
      return this.char1 + this.char2 + this.char3 + this.char4 + this.char5 + this.char6 + this.char7 + this.char8 + this.char9 + this.char10;
    }

}
