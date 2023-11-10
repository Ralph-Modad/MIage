import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { AssignmentsComponent } from './assignments/assignments.component';
import { RenduDirective } from './shared/rendu.directive';
import { NonRenduDirective } from './shared/non-rendu.directive';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list'; // Add this line
import { MatCardModule } from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { AssignmentDetailComponent } from './assignments/assignment-detail/assignment-detail.component';
import { AddAssignmentComponent } from './assignments/add-assignment/add-assignment.component';
import { RouterModule, Routes } from '@angular/router';
import { EditAssignmentComponent } from './assignments/edit-assignment/edit-assignment.component';
import { authGuard } from './shared/auth.guard';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { LoginComponent } from './login/login.component';


const routes: Routes = [
  { path: 'home', component: AssignmentsComponent, canActivate: [authGuard] },
  { path: 'add', component: AddAssignmentComponent },
  {
    path: 'assignment/:id',
    component: AssignmentDetailComponent,
    canActivate: [authGuard],
  },
  {
    path: 'assignment/:id/edit',
    component: EditAssignmentComponent,
    canActivate: [authGuard],
  },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    AssignmentsComponent,
    RenduDirective,
    NonRenduDirective,
    AssignmentDetailComponent,
    AddAssignmentComponent,
    EditAssignmentComponent,
    LoginComponent


  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule, MatIconModule, MatDividerModule,
    MatFormFieldModule, MatInputModule,
    FormsModule,
    MatSidenavModule, MatToolbarModule, MatListModule, MatDatepickerModule, MatNativeDateModule,MatCardModule, MatCheckboxModule,RouterModule.forRoot(routes), MatSlideToggleModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
