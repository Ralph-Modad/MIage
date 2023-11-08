import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Assignment } from '../assignments.model';
import { AssignmentsService } from '../../shared/assignments.service';
@Component({
  selector: 'app-assignment-detail',
  templateUrl: './assignment-detail.component.html',
  styleUrls: ['./assignment-detail.component.css'],
})
export class AssignmentDetailComponent {
  @Input() assignmentTransmis?: Assignment;
  @Output() deleteAssignment = new EventEmitter<Assignment>();

  constructor(private assignmentService: AssignmentsService) { }

  assignmentRendu = false;

  onAssignmentRendu() {
    if (this.assignmentTransmis) {
      this.assignmentTransmis.rendu = true;
      this.assignmentService.updateAssignment(this.assignmentTransmis).subscribe((message) => {
        console.log(message);
      });
    }
  }

  onDeleteAssignment() {
    if (this.assignmentTransmis) {
      this.assignmentService.deleteAssignment(this.assignmentTransmis).subscribe((message) => {
        console.log(message);
        this.assignmentTransmis = undefined;
      });
    }
  }



}
