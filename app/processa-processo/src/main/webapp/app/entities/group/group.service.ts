import axios from 'axios';

import { IGroup } from '@/shared/model/group.model';

const baseApiUrl = 'api/groups';

export default class GroupService {
  public find(id: number): Promise<IGroup> {
    return new Promise<IGroup>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
