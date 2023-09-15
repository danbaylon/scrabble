import { Component, OnInit } from '@angular/core';
import { Entry } from '../entry';
import { Input } from '../input';
import { EntryService } from '../entry.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-save-entry',
  templateUrl: './save-entry.component.html',
  styleUrls: ['./save-entry.component.css']
})
export class SaveEntryComponent implements OnInit {

  input : Input = new Input();
  computedScore : number = 0;
  scoreMapping = new Map<string, number>();
  entry : Entry = new Entry;
  msg : string = '';
  errMsg : string = '';

  ngOnInit(): void { }

  constructor(private entryService: EntryService, private router: Router) {
          this.scoreMapping.set('A', 1);
          this.scoreMapping.set('B', 3);
          this.scoreMapping.set('C', 3);
          this.scoreMapping.set('D', 2);
          this.scoreMapping.set('E', 1);
          this.scoreMapping.set('F', 4);
          this.scoreMapping.set('G', 2);
          this.scoreMapping.set('H', 4);
          this.scoreMapping.set('I', 1);
          this.scoreMapping.set('J', 8);
          this.scoreMapping.set('K', 6);
          this.scoreMapping.set('L', 1);
          this.scoreMapping.set('M', 3);
          this.scoreMapping.set('N', 1);
          this.scoreMapping.set('O', 1);
          this.scoreMapping.set('P', 3);
          this.scoreMapping.set('Q', 10);
          this.scoreMapping.set('R', 1);
          this.scoreMapping.set('S', 1);
          this.scoreMapping.set('T', 1);
          this.scoreMapping.set('U', 1);
          this.scoreMapping.set('V', 4);
          this.scoreMapping.set('W', 4);
          this.scoreMapping.set('X', 8);
          this.scoreMapping.set('Y', 4);
          this.scoreMapping.set('Z', 10);
      }

  onSubmit() {
      this.entry.word = this.input.generateWord();
      this.entry.score = this.computedScore;
      if(this.entry.word != '') {

        console.log('Submitting this entry : ', this.entry);
        this.entryService.saveEntry(this.entry)
                        .subscribe( data =>{
                                    this.msg = 'Entry successfully added.';
                                    setTimeout(() => {
                                      this.msg='';
                                    }, 2000);
                                   },
                                   error => {
                                    this.errMsg = 'Error encountered: ' + error.error.error;
                                    console.log(this.errMsg);
                                    setTimeout(() => {
                                           this.errMsg='';
                                        }, 2000);
                                   });
        this.clearForm();
      } else {
        this.errMsg = 'Error encountered: input needed.';
        console.log(this.errMsg);
                                            setTimeout(() => {
                                                   this.errMsg='';
                                                }, 2000);
      }
  }

  clearForm() {
    console.log('Clearing this input : ', this.input);
    this.input.clear();
    this.computedScore = 0;
    this.msg = '';
    this.errMsg = '';
  }

  onTopScores() {
    this.goToTop10Entries();
  }

  onPaste(e : any) {
      e.preventDefault();
      return false;
  }

  alphaOnly (e : any) {
    var regex = new RegExp("[a-zA-Z]+");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      return true;
    }
    e.preventDefault();
      return false;
  }

  compute(e : any) {

    const val1 = this.scoreMapping.get(this.input.char1.toUpperCase())! || 0;
    const val2 = this.scoreMapping.get(this.input.char2.toUpperCase())! || 0;
    const val3 = this.scoreMapping.get(this.input.char3.toUpperCase())! || 0;
    const val4 = this.scoreMapping.get(this.input.char4.toUpperCase())! || 0;
    const val5 = this.scoreMapping.get(this.input.char5.toUpperCase())! || 0;
    const val6 = this.scoreMapping.get(this.input.char6.toUpperCase())! || 0;
    const val7 = this.scoreMapping.get(this.input.char7.toUpperCase())! || 0;
    const val8 = this.scoreMapping.get(this.input.char8.toUpperCase())! || 0;
    const val9 = this.scoreMapping.get(this.input.char9.toUpperCase())! || 0;
    const val10 = this.scoreMapping.get(this.input.char10.toUpperCase())! || 0;

    this.computedScore = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9 + val10;

  }

  private goToTop10Entries(){
    this.router.navigate(['/list-entry']);
  }
}
