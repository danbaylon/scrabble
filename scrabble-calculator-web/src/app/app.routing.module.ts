import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SaveEntryComponent } from './save-entry/save-entry.component';
import { ListEntryComponent } from './list-entry/list-entry.component';

const routes: Routes = [
  {path: 'save-entry', component: SaveEntryComponent},
  {path: 'list-entry', component: ListEntryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
