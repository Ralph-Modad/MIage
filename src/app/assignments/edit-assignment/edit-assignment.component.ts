import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Assignment } from '../assignments.model';
import { AssignmentsService } from '../../shared/assignments.service';

@Component({
  selector: 'app-edit-assignment',
  templateUrl: './edit-assignment.component.html',
  styleUrls: ['./edit-assignment.component.css'],
})
export class EditAssignmentComponent implements OnInit {
  assignment!: Assignment | undefined;
  nomAssignment!: string;
  dateDeRendu!: Date;

  constructor(
    private assignmentService: AssignmentsService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.params['id'];
    this.assignmentService.getAssignment(id).subscribe((assignment) => {
      this.assignment = assignment;
    });
    const paramsHttp = this.route.snapshot.queryParams;
    const fragment = this.route.snapshot.fragment;
    console.log('paramsHttp');
    console.log(paramsHttp);
    console.log('fragment');
    console.log(fragment);

  }

  getAssignment() {
    // on récupère l'id dans le snapshot passé par le routeur
    // le "+" force l'id de type string en "number"
    const id = +this.route.snapshot.params['id'];

    this.assignmentService.getAssignment(id).subscribe((assignment) => {
      if (!assignment) return;
      this.assignment = assignment;
      // Pour pré-remplir le formulaire
      this.nomAssignment = assignment.nom;
      this.dateDeRendu = assignment.dateDeRendu;
    });
  }
  onSaveAssignment( event: any) {
    event?.preventDefault(); // pour éviter le rechargement de la page
    if (!this.assignment) return;

    // on récupère les valeurs dans le formulaire

    this.assignmentService
      .updateAssignment(this.assignment)
      .subscribe((message) => {
        console.log(message);

        // navigation vers la home page
        this.router.navigate(['home']);
      });
  }
}
