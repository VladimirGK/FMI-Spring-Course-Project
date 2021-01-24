axios.interceptors.request.use( config => {
    const user = JSON.parse(localStorage.getItem('user'));
  
    if(user && user.user.token){
      const token = 'Bearer ' + user.user.token;
      config.headers.Authorization =  token;
    }
  
    return config;
  });
  