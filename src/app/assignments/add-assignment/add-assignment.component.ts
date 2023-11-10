import { Component,/* EventEmitter, Output*/ } from '@angular/core';
import { Assignment } from '../assignments.model';
import { AssignmentsService } from '../../shared/assignments.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-assignment',
  templateUrl: './add-assignment.component.html',
  styleUrls: ['./add-assignment.component.css'],
})
export class AddAssignmentComponent {
  // Evenement qu'on enverra au père avec la soumission
  // du formulaire
  //@Output() nouvelAssignment = new EventEmitter<Assignment>();
  nouvelAssignment: Assignment = new Assignment();
  // pour le formulaire
  nomDevoir = '';
  dateDeRendu?: Date = undefined;

  constructor(
    private AssignmentService: AssignmentsService,
    private router: Router
  ) {}

  onSubmit(event: any) {
    event.preventDefault(); // pour ne pas recharger la page à la soumission du formulaire
    let a = new Assignment();
    a.nom = this.nomDevoir;
    a.id = this.AssignmentService.getNewId();
    if (this.dateDeRendu) a.dateDeRendu = this.dateDeRendu;

    a.rendu = false;

    //this.assignments.push(a);
    // this.nouvelAssignment.emit(a);

    this.AssignmentService.addAssignment(a).subscribe((reponse) => {
      console.log(reponse);
      // On navigue vers la page d'accueil
      this.router.navigate(['home']);
    });
  }
}
