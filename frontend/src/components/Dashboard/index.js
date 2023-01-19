import React from 'react';
import { useLocalState } from '../../util/useLocalStorage';

const Dashboard = () => {
    const [jwt, setJwt] = useLocalState("", "jwt")
    return (
        <div>
            token: ${jwt}
        </div>
    );
};

export default Dashboard;