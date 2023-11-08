import { Injectable } from '@angular/core';
import { Assignment } from '../assignments/assignments.model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AssignmentsService {
  constructor() {}

  getAssignments(): Observable<Assignment[]> {
    return of(this.assignments);
  }

  addAssignment(assignment: Assignment): Observable<String> {
    this.assignments.push(assignment);
    return of("Assignment ajouté");
  }

  deleteAssignment(assignment: Assignment): Observable<String> {
    const pos = this.assignments.indexOf(assignment);
    this.assignments.splice(pos, 1);
    return of("Assignment supprimé");
  }

  updateAssignment(assignment: Assignment): Observable<String> {
    this.assignments.forEach((a,index) => {
      if (a === assignment) {
        this.assignments[index] = assignment;
      }
    });
    return of("Assignment mis à jour");
  }

  assignments: Assignment[] = [
    {
      nom: 'Devoir Angular de Buffa',
      dateDeRendu: new Date('2023-09-30'),
      rendu: false,
    },
    {
      nom: 'Devoir SQL de Mopolo',
      dateDeRendu: new Date('2023-10-30'),
      rendu: false,
    },
    {
      nom: 'Devoir gestion de Tunsi',
      dateDeRendu: new Date('2023-08-30'),
      rendu: true,
    },
  ];
}
