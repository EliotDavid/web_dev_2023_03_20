import User from "src/interfaces/User.Interface";
import { create } from "zustand";

interface IUserStore {
    user: User | null;
    setUser: (user: User) => void;
    resetUser: () => void;
}

const useStore = create<IUserStore>((set) => ({
    user: null,
    setUser: (user: User) => set((state) => ({ ...state, user })),
    resetUser: () => set((state) => ({ ...state, user: null })),
}));

export default useStore;