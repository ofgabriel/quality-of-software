import axios from 'axios';
import { TaskGroupDefinitionContext } from './task-group-definition.model';

const baseApiUrl = 'api/suit-process/task-group-definition';

export default class TaskGroupDefinitionService {
  public loadContext(taskId: number): Promise<TaskGroupDefinitionContext> {
    return new Promise<TaskGroupDefinitionContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskGroupDefinitionContext> {
    return new Promise<TaskGroupDefinitionContext>((resolve, reject) => {
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

  public complete(taskGroupDefinitionContext: TaskGroupDefinitionContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskGroupDefinitionContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
