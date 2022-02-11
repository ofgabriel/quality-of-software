import axios from 'axios';

import { ILawyer } from '@/shared/model/lawyer.model';

const baseApiUrl = 'api/lawyers';

export default class LawyerService {
  public find(id: number): Promise<ILawyer> {
    return new Promise<ILawyer>((resolve, reject) => {
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
