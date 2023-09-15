import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'
import { ActivatedRoute, Router } from '@angular/router';

import { AppComponent } from './app.component';
import { SaveEntryComponent } from './save-entry/save-entry.component';
import { ListEntryComponent } from './list-entry/list-entry.component';
import { AppRoutingModule } from './app.routing.module'

@NgModule({
  declarations: [
    AppComponent,
    SaveEntryComponent,
    ListEntryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor(private router: Router, private activeRoute: ActivatedRoute) {
     this.router.navigate(['save-entry']);
    }

}
