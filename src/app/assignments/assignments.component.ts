
import { Component, OnInit } from '@angular/core';
import { Assignment } from './assignments.model';
import { AssignmentsService } from '../shared/assignments.service';

@Component({
  selector: 'app-assignments',
  templateUrl: './assignments.component.html',
  styleUrls: ['./assignments.component.css'],
})
export class AssignmentsComponent implements OnInit {
  // Properties
  titre = "Formulaire d'ajout de devoir";
  color = 'green';
  id = 'monParagraphe';
  boutonDesactive = true;
  formVisible = false;
  assignmentSelectionne?: Assignment;
  assignments!: Assignment[];

  // Constructor
  constructor(private assignmentService: AssignmentsService) { }

  // Methods
  ngOnInit() {
    this.getAssignments();
  }

  getAssignments(): void {
    this.assignmentService
      .getAssignments()
      .subscribe((assignments) => (this.assignments = assignments));
  }

  getDescription() {
    return 'Je suis un sous composant';
  }

  getColor(a: any) {
    if (a.rendu) return 'green';
    else return 'red';
  }

  assignmentClique(a: Assignment) {
    this.assignmentSelectionne = a;
  }

  onAddAssignmentBtnClick() {
    this.formVisible = true;
  }

  onNouvelAssignment(event: Assignment) {
    this.assignmentService.addAssignment(event).subscribe((message) => {
      console.log(message);
      this.formVisible = false;
    }
    );
  }

  onDeleteAssignment(a: Assignment) {
    // position de l'assignment à supprimer, dans le tableau
    const pos = this.assignments.indexOf(a);

    // on le supprime avec ma méthode standard splice
    // sur les tableaux javascript. Elle prend en parametre
    // la position de l'élément à supprimer et le nombre d'éléments
    // à supprimer à partir de cette position
    this.assignments.splice(pos, 1);
  }
}
