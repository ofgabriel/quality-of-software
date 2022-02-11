import axios from 'axios';
import { TaskDisplayDetailsContext } from './task-display-details.model';

const baseApiUrl = 'api/suit-process/task-display-details';

export default class TaskDisplayDetailsService {
  public loadContext(taskId: number): Promise<TaskDisplayDetailsContext> {
    return new Promise<TaskDisplayDetailsContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskDisplayDetailsContext> {
    return new Promise<TaskDisplayDetailsContext>((resolve, reject) => {
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

  public complete(taskDisplayDetailsContext: TaskDisplayDetailsContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskDisplayDetailsContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
