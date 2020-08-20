<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Publicaciones;

class PublicacionesController extends Controller
{
    public function index(){
        $p = Publicaciones::all();
        return response()->json($p, 200);
    }

    public function store(Request $request){
        $p = new Publicaciones($request->all());
        $p->save();
        return response()->json($p, 200);
    }

}
