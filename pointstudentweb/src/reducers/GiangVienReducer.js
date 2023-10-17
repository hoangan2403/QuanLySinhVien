import cookie from "react-cookies";

const GiangVienReducer = (currentState, action) => {
    switch (action.type) {
        case "login_gv":         
            return action.payLoad;
        case "logout_gv":         
            return null;
    }
    return currentState;
}

export default GiangVienReducer;