import React, { useEffect, useState } from 'react';
import { useLocalState } from '../../util/useLocalStorage';
import SeanceList from './SeanceList';

const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [seances, setSeances] = useState();
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const getData = async () => {
            try {
            const response = await fetch("/seances/all", {method: "get",})
            
            if(response.status === 200) {
                const data = await response.json();
                setSeances(data);
                setIsLoading(false);
              } else {
                throw new Error();
              }
        } catch(error) {
            console.log(error);
        }
      }
      getData();
    },[])
    console.log(seances)

    return (isLoading ? (<div>Loading...</div>) : <SeanceList seances={seances} />
    );
};

export default Dashboard;