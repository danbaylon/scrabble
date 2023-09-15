import { Component, OnInit } from '@angular/core';
import { Entry } from '../entry'
import { EntryService } from '../entry.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-entry',
  templateUrl: './list-entry.component.html',
  styleUrls: ['./list-entry.component.css']
})
export class ListEntryComponent implements OnInit {
   entries: Entry[] = [];

    constructor(private entryService: EntryService, private router: Router) { }

    ngOnInit(): void {
      this.getTop10Entries();
    }

    private getTop10Entries(){
       this.entryService.getTop10EntryList().subscribe(data => {
            this.entries = data;
          });
    }

    backToSave() {
      this.router.navigate(['/save-entry']);
    }

}
