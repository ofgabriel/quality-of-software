import axios from 'axios';
import { TaskWatcherDefinitionContext } from './task-watcher-definition.model';

const baseApiUrl = 'api/suit-process/task-watcher-definition';

export default class TaskWatcherDefinitionService {
  public loadContext(taskId: number): Promise<TaskWatcherDefinitionContext> {
    return new Promise<TaskWatcherDefinitionContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskWatcherDefinitionContext> {
    return new Promise<TaskWatcherDefinitionContext>((resolve, reject) => {
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

  public complete(taskWatcherDefinitionContext: TaskWatcherDefinitionContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskWatcherDefinitionContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
