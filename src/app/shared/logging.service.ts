import { Injectable } from '@angular/core';
import { Assignment } from '../assignments/assignments.model';

@Injectable({
  providedIn: 'root'
})
export class LoggingService {

  constructor() { }

  log(assignmentName :Assignment, action :string) {
    console.log("Assignment " + assignmentName + " " + action);
  }
}
