import { Injectable } from '@angular/core';
import { Assignment } from '../assignments/assignments.model';
import { LoggingService } from './logging.service';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AssignmentsService {
  constructor(
    private loggingService: LoggingService,
  ) {}

  getAssignments(): Observable<Assignment[]> {
    return of(this.assignments);
  }

  addAssignment(assignment: Assignment): Observable<String> {
    this.assignments.push(assignment);
    this.loggingService.log(assignment, 'ajouté');
    return of("Assignment ajouté");
  }

  deleteAssignment(assignment: Assignment): Observable<String> {
    const pos = this.assignments.indexOf(assignment);
    this.assignments.splice(pos, 1);
    this.loggingService.log(assignment, 'supprimé');
    return of("Assignment supprimé");
  }

  getAssignment(id: number): Observable<Assignment | undefined> {
    return of(this.assignments.find((a) => a.id === id));
  }

  updateAssignment(assignment: Assignment): Observable<String> {
    this.assignments.forEach((a,index) => {
      if (a === assignment) {
        this.assignments[index] = assignment;
        this.loggingService.log(assignment, 'mis à jour');
      }
    });
    return of("Assignment mis à jour");
  }

  getNewId():number {
    return this.assignments.length + 1;
  }

  assignments: Assignment[] = [
    {
      id: 1,
      nom: ' Angular de Buffa',
      dateDeRendu: new Date('2023-09-30'),
      rendu: false,
    },
    {
      id: 2,
      nom: ' SQL de Mopolo',
      dateDeRendu: new Date('2023-10-30'),
      rendu: false,
    },
    {
      id: 3,
      nom: ' Gestion de Tunsi',
      dateDeRendu: new Date('2023-08-30'),
      rendu: true,
    },
  ];
}
