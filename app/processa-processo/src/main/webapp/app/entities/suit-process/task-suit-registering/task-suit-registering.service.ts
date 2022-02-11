import axios from 'axios';
import { TaskSuitRegisteringContext } from './task-suit-registering.model';

const baseApiUrl = 'api/suit-process/task-suit-registering';

export default class TaskSuitRegisteringService {
  public loadContext(taskId: number): Promise<TaskSuitRegisteringContext> {
    return new Promise<TaskSuitRegisteringContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskSuitRegisteringContext> {
    return new Promise<TaskSuitRegisteringContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskSuitRegisteringContext: TaskSuitRegisteringContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskSuitRegisteringContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
