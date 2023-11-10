import { Component, EventEmitter /*, Input*/, Output } from '@angular/core';
import { Assignment } from '../assignments.model';
import { AssignmentsService } from '../../shared/assignments.service';
import { ActivatedRoute, Router } from '@angular/router';
import { query } from '@angular/animations';
import { AuthService } from '../../shared/auth.service';
@Component({
  selector: 'app-assignment-detail',
  templateUrl: './assignment-detail.component.html',
  styleUrls: ['./assignment-detail.component.css'],
})
export class AssignmentDetailComponent {
  // @Input() assignmentTransmis?: Assignment;
  @Output() deleteAssignment = new EventEmitter<Assignment>();

  assignmentTransmis?: Assignment;
  constructor(
    private assignmentService: AssignmentsService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}
  /*
On récupere l'id de l'assignment dans l'url et on va chercher l'assignment correspondant dans le service
L'url est composée de String, il faut donc convertir l'id en nombre avec le + devant
*/
  ngOnInit(): void {
    const id = +this.route.snapshot.params['id'];
    this.assignmentService.getAssignment(id).subscribe((assignment) => {
      this.assignmentTransmis = assignment;
    });
  }

  assignmentRendu = false;

  onAssignmentRendu() {
    console.log('assignment rendu');
    if (this.assignmentTransmis) {
      this.assignmentTransmis.rendu = true;
      this.assignmentService
        .updateAssignment(this.assignmentTransmis)
        .subscribe((message) => {
          console.log(message);
          this.assignmentTransmis = undefined;
          this.router.navigate(['home']);
        });
    }
  }

  onDeleteAssignment() {
    if (this.assignmentTransmis) {
      this.assignmentService
        .deleteAssignment(this.assignmentTransmis)
        .subscribe((message) => {
          console.log(message);
          this.assignmentTransmis = undefined;
          this.router.navigate(['home']);
        });
    }
  }
  // onClickEdit() {
  //   if (this.assignmentTransmis) {
  //     this.router.navigate(['/assignment', this.assignmentTransmis.id, 'edit'], {
  //       queryParams: { nom: this.assignmentTransmis.nom },
  //       fragment: 'edition',
  //     });
  //   }
  // }

  onClickEdit() {
    if (this.assignmentTransmis) {
      this.router.navigate(
        ['/assignment', this.assignmentTransmis.id, 'edit'],
        {
          queryParams: { nom: this.assignmentTransmis.nom },
          fragment: 'edition',
        }
      );
    }
  }

  isAdmin() {
    //if the connected user is admin return true else return false
    return this.authService.isAdmin();
  }
}
