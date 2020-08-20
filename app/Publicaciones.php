<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Publicaciones extends Model
{
    protected $fillable = [
        'nombre', 'descripcion',
    ];
}
