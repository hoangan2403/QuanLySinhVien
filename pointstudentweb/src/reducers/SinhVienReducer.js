import cookie from "react-cookies";

const SinhVienReducer = (currentState, action) => {
    switch (action.type) {
        case "login_sv":

            return action.payLoad;
        case "logout_sv":
            
            return null;
    }
    return currentState;
}


export default SinhVienReducer;